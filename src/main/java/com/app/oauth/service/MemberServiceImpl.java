package com.app.oauth.service;

import com.app.oauth.domain.dto.JwtTokenDTO;
import com.app.oauth.domain.dto.MemberDTO;
import com.app.oauth.domain.vo.MemberVO;
import com.app.oauth.domain.vo.SocialMemberVO;
import com.app.oauth.exception.MemberException;
import com.app.oauth.repository.MemberDAO;
import com.app.oauth.repository.SocialMemberDAO;
import com.app.oauth.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class MemberServiceImpl implements MemberService {

    private final MemberDAO memberDAO;
    private final SocialMemberDAO socialMemberDAO;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    //    회원 가입
    @Override
    public Map<String, Object> join(MemberDTO memberDTO) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> claims = new HashMap<>();

        if(memberDAO.existsMemberByMemberEmail(memberDTO.getMemberEmail())){
            throw new MemberException("중복된 이메일 입니다.", HttpStatus.BAD_REQUEST);
        }

        MemberVO memberVO = MemberVO.from(memberDTO);
        SocialMemberVO socialMemberVO = SocialMemberVO.from(memberDTO);

//        socialMemberVO.getSocialMemberProvider().equals("local")
        if("local".equals(socialMemberVO.getSocialMemberProvider())){
            memberVO.setMemberPassword(passwordEncoder.encode(memberDTO.getMemberPassword()));
        }

        memberDAO.save(memberVO);
        socialMemberVO.setMemberId(memberVO.getId());

        socialMemberDAO.save(socialMemberVO);

        result.put("success", true);
        result.put("message", "회원가입이 완료되었습니다.");

        claims.put("id", memberVO.getId());
        claims.put("memberEmail", memberVO.getMemberEmail());
        claims.put("memberProvider", socialMemberVO.getSocialMemberProvider());

        result.put("claims", claims);

        return result;
    }

    //    일반 로그인
    @Override
    public JwtTokenDTO login(MemberDTO memberDTO) {
        // 사용자가 맞는지 (이메일, 비밀번호, 프로바이더(local)

        // elary return
        MemberVO memberVO = MemberVO.from(memberDTO);
        // 회원 유무 검사
        MemberDTO foundMember = memberDAO
                .findMemberByMemberEmail(memberDTO.getMemberEmail())
                .orElseThrow(() -> {
                    throw new MemberException("회원이 아닙니다.", HttpStatus.BAD_REQUEST);
                });

        // 회원 비밀번호 일치 검사
        // 화면에서 받은 비밀번호, DB에 있는 비밀번호 검사
        if(!passwordEncoder.matches(memberVO.getMemberPassword(), foundMember.getMemberPassword())){
            throw new MemberException("비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
        }

        // 토큰 생성(access, refresh)
        Map<String, String> claims = new HashMap<>();
        claims.put("id", foundMember.getId().toString());
        claims.put("memberEmail", foundMember.getMemberEmail());
        claims.put("memberProvider", "local");

        String accessToken = jwtTokenUtil.generateAccessToken(claims);
        String refreshToken = jwtTokenUtil.generateRefreshToken(claims);

        JwtTokenDTO jwtTokenDTO = new JwtTokenDTO();
        jwtTokenDTO.setAccessToken(accessToken);
        jwtTokenDTO.setRefreshToken(refreshToken);

        return jwtTokenDTO;
    }

    //    소셜 로그인
    @Override
    public void socialLogin(MemberDTO memberDTO) {

    }
}


















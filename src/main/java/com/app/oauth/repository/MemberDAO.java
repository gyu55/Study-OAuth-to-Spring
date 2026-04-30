package com.app.oauth.repository;

import com.app.oauth.domain.dto.MemberDTO;
import com.app.oauth.domain.vo.MemberVO;
import com.app.oauth.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberDAO {
    private final MemberMapper memberMapper;

//    회원 추가
    public void save(MemberVO memberVO){
        memberMapper.insert(memberVO);
    }

//    회원 조회(ID)
    public Optional<MemberDTO> findMemberById(Long id){
        return Optional.ofNullable(memberMapper.select(id));
    }

//    회원 조회(memberEmail)
    public Optional<MemberDTO> findByMemberEmailAndSocialMemberProvider(MemberDTO memberDTO){
        return Optional.ofNullable(memberMapper.selectByMemberEmailAndSocialMemberProvider(memberDTO));
    }

//    회원가입 여부 조회(memberEmail)
    public boolean existsMemberByMemberEmailAndSocialMemberProvider(MemberDTO memberDTO){
        return memberMapper.existsMemberByMemberEmailAndSocialMemberProvider(memberDTO);
    }

//    회원 정보 수정
    public void update(MemberVO memberVO){
        memberMapper.update(memberVO);
    }

//    회원 썸네일 변경
    public void updateMemberPicture(MemberVO memberVO){
        memberMapper.updateMemberPicture(memberVO);
    }

//    회원 삭제
    public void delete(Long id){
        memberMapper.delete(id);
    }



}

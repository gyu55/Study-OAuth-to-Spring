package com.app.oauth.service;


import com.app.oauth.domain.dto.JwtTokenDTO;
import com.app.oauth.domain.dto.MemberDTO;

import java.util.Map;

public interface MemberService {
//    회원가입
    public Map<String, Object> join(MemberDTO memberDTO);

//    로컬 로그인
    public JwtTokenDTO login(MemberDTO memberDTO);

//    소셜 로그인
    public void socialLogin(MemberDTO memberDTO);

//    회원 수정
//    회원 탈퇴
}

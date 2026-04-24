package com.app.oauth.service;


import com.app.oauth.domain.dto.JwtTokenDTO;
import com.app.oauth.domain.dto.MemberDTO;
import com.app.oauth.domain.dto.response.ApiResponseDTO;

public interface AuthService {
    // 로컬 로그인
    public JwtTokenDTO login(MemberDTO memberDTO);

    // 소셜 로그인
    public void socialLogin(MemberDTO memberDTO);

    // Redis에 refresh Token 저장 -> 토큰이 맞는지 아닌지 검사하기 위함
    // Redis에 저장된 refresh Token이 유효한지 검증
    // Redis에 블랙리스트를 등록 (로그아웃 시 토큰 무효화)
    // Redis에 등록된 블랙리스트인지 검증 -> refresh Token이 살아있는지 아닌지 알아야 함
    // refresh 토큰을 검증하고, 새로운 accessToken 발급 서비스
    public JwtTokenDTO reissueAccessToken(JwtTokenDTO jwtTokenDTO);

}

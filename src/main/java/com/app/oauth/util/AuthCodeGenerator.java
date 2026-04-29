package com.app.oauth.util;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class AuthCodeGenerator {

    // SecureRandom -> 보안적으로 안전(랜덤 추측 불가)
    private static final SecureRandom random = new SecureRandom();

    public static String generateAuthCode(){
        // 100000 ~ 999999 범위 랜덤한 6자리 숫자
        int code = random.nextInt(999999) + 100000;
        return String.valueOf(code);
    }
}

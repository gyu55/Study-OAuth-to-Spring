package com.app.oauth.util;

import com.app.oauth.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class AuthServiceTests {

    @Autowired
    private AuthService authService;

    @Test
    public void sendMemberPhoneCodeTest(){
        authService.sendMemberPhoneVerificationCode("01056038560");
    }

    @Test
    public void verifyMemberPhoneCodeTest(){
        authService.verifyMemberPhoneVerificationCode("01056038560", "1234");
    }
}

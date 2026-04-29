package com.app.oauth.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class SmsUtilTests {

    @Autowired
    private SmsUtil smsUtil;

    @Test
    public void smsTest(){
        smsUtil.sendOneMemberPhone("01056038560", "창을 줄이면 헤더 레이아웃이 뒤틀려요.");
    }
}


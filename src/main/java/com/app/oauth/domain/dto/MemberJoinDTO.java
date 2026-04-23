package com.app.oauth.domain.dto;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class MemberJoinDTO {
    private Long id;
    private Long memberId;
    private String memberEmail;
    private String memberPassword;
    private String memberPicture;
    private String memberName;
    private String memberNickname;
    private String socialMemberProviderId;
    private String socialMemberProvider;
}

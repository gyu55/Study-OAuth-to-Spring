package com.app.oauth.domain.vo;

import com.app.oauth.domain.dto.MemberJoinDTO;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class SocialMemberVO {
    private Long id;
    private String socialMemberProviderId;
    private String socialMemberProvider;
    private Long memberId;

    {
        this.setSocialMemberProvider("local");
    }

    public static SocialMemberVO from(MemberJoinDTO memberJoinDTO){
        SocialMemberVO socialMemberVO = new SocialMemberVO();
        socialMemberVO.setSocialMemberProviderId(memberJoinDTO.getSocialMemberProviderId());
        socialMemberVO.setSocialMemberProvider(memberJoinDTO.getSocialMemberProvider());
        socialMemberVO.setMemberId(memberJoinDTO.getMemberId());
        socialMemberVO.setId(memberJoinDTO.getId());
        return socialMemberVO;
    }
}

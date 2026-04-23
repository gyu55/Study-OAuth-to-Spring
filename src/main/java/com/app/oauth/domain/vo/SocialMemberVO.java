package com.app.oauth.domain.vo;

import com.app.oauth.domain.dto.MemberDTO;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class SocialMemberVO {
    private Long id;
    private String socialMemberProviderId;
    private String socialMemberProvider;
    private Long memberId;

    public static SocialMemberVO from(MemberDTO memberDTO){
        SocialMemberVO socialMemberVO = new SocialMemberVO();
        socialMemberVO.setId(memberDTO.getId());
        socialMemberVO.setSocialMemberProviderId(memberDTO.getSocialMemberProviderId());
        socialMemberVO.setSocialMemberProvider(memberDTO.getSocialMemberProvider() != null ? memberDTO.getSocialMemberProvider() : "local");
        socialMemberVO.setMemberId(memberDTO.getMemberId());
        return socialMemberVO;
    }
}

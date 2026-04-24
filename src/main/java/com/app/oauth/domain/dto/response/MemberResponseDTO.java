package com.app.oauth.domain.dto.response;

import com.app.oauth.domain.dto.MemberDTO;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class MemberResponseDTO {
    private Long id;
    private String memberEmail;
    private String memberNickname;
    private String memberName;
    private String memberPicture;
    private String socialMemberProviderId;
    private String socialMemberProvider;
    private Long memberId;

    public static MemberResponseDTO from(MemberDTO memberDTO){

        MemberResponseDTO memberResponseDTO = new MemberResponseDTO();
        memberResponseDTO.setId(memberDTO.getId());
        memberResponseDTO.setMemberEmail(memberDTO.getMemberEmail());
        memberResponseDTO.setMemberNickname(memberDTO.getMemberNickname());
        memberResponseDTO.setMemberName(memberDTO.getMemberName());
        memberResponseDTO.setMemberPicture(memberDTO.getMemberPicture());
        memberResponseDTO.setSocialMemberProviderId(memberDTO.getSocialMemberProviderId());
        memberResponseDTO.setSocialMemberProvider(memberDTO.getSocialMemberProvider());
        memberResponseDTO.setMemberId(memberDTO.getMemberId());

        return memberResponseDTO;
    }

}

package com.app.oauth.domain.vo;

import com.app.oauth.domain.dto.MemberDTO;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class MemberVO {
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberPicture;
    private String memberName;
    private String memberNickname;

    {
//        초기화 블럭
        this.setMemberPicture("/default.jpg");
        this.setMemberNickname("개복치 1단계");
    }

    public static MemberVO from(MemberDTO memberDTO){
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberEmail(memberDTO.getMemberEmail());
        memberVO.setMemberPassword(memberDTO.getMemberPassword());
        memberVO.setMemberPicture(memberDTO.getMemberPicture() != null ? memberDTO.getMemberPicture() : "/default.jpg");
        memberVO.setMemberName(memberDTO.getMemberName());
        memberVO.setMemberNickname(memberDTO.getMemberNickname() != null ? memberDTO.getMemberNickname() : "개복치 1단계");
        memberVO.setId(memberDTO.getId());
        return memberVO;
    }
}

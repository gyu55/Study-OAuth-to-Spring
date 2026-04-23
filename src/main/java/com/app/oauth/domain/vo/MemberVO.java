package com.app.oauth.domain.vo;

import com.app.oauth.domain.dto.MemberJoinDTO;
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

    public static MemberVO from(MemberJoinDTO memberJoinDTO){
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberEmail(memberJoinDTO.getMemberEmail());
        memberVO.setMemberPassword(memberJoinDTO.getMemberPassword());
        memberVO.setMemberPicture(memberJoinDTO.getMemberPicture());
        memberVO.setMemberName(memberJoinDTO.getMemberName());
        memberVO.setMemberNickname(memberJoinDTO.getMemberNickname());
        memberVO.setId(memberJoinDTO.getId());
        return memberVO;
    }
}

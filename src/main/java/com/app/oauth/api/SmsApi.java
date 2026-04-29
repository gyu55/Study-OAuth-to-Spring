package com.app.oauth.api;


import com.app.oauth.domain.dto.request.VerificationRequestDTO;
import com.app.oauth.domain.dto.response.ApiResponseDTO;
import com.app.oauth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sms")
@RequiredArgsConstructor
public class SmsApi {

    private final AuthService authService;
    // 핸드폰 전송
    @PostMapping("/phone/verification-code")
    public ResponseEntity<ApiResponseDTO> sendMemberPhoneVerificationCode(
            @RequestBody VerificationRequestDTO verificationRequestDTO
            ){
        String memberPhone = verificationRequestDTO.getMemberPhone();
        ApiResponseDTO apiResponseDTO = null;
        if(authService.sendMemberPhoneVerificationCode(memberPhone)){
            apiResponseDTO = ApiResponseDTO.of("메세지가 발송되었습니다.", HttpStatus.OK);
        }else{
            apiResponseDTO = ApiResponseDTO.of("휴대폰 번호를 확인해주세요.", HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.status(HttpStatus.OK).body(apiResponseDTO);
    }

    // 핸드폰 인증코드 검증
    @PostMapping("/phone/verification-code/verify")
    public ResponseEntity<ApiResponseDTO> verificationMemberPhoneVerificationCode(
            @RequestBody VerificationRequestDTO verificationRequestDTO
    ){
        String memberPhone = verificationRequestDTO.getMemberPhone();
        String code = verificationRequestDTO.getCode();
        ApiResponseDTO apiResponseDTO = null;
        if(authService.verifyMemberPhoneVerificationCode(memberPhone, code)){
            apiResponseDTO = ApiResponseDTO.of(true, "인증이 완료되었습니다.");
        }else{
            apiResponseDTO = ApiResponseDTO.of(false, "인증번호를 확인해주세요.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(apiResponseDTO);
    }

    // 이메일 전송
    @PostMapping("/email/verification-code")
    public ResponseEntity<ApiResponseDTO> sendMemberEmailVerificationCode(
            @RequestBody VerificationRequestDTO verificationRequestDTO
    ){
        String memberEmail = verificationRequestDTO.getMemberEmail();
        ApiResponseDTO apiResponseDTO = null;
        if(authService.sendMemberEmailVerificationCode(memberEmail)){
            apiResponseDTO = ApiResponseDTO.of("이메일이 발송되었습니다.", HttpStatus.OK);
        }else{
            apiResponseDTO = ApiResponseDTO.of("이메일 주소를 확인해주세요.", HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.status(HttpStatus.OK).body(apiResponseDTO);
    }

    @PostMapping("/email/verification-code/verify")
    public ResponseEntity<ApiResponseDTO> verificationMemberEmailVerificationCode(
            @RequestBody VerificationRequestDTO verificationRequestDTO
    ){
        String memberEmail = verificationRequestDTO.getMemberEmail();
        String code = verificationRequestDTO.getCode();
        ApiResponseDTO apiResponseDTO = null;
        if(authService.verifyMemberEmailVerificationCode(memberEmail, code)){
            apiResponseDTO = ApiResponseDTO.of(true, "인증이 완료되었습니다.");
        }else{
            apiResponseDTO = ApiResponseDTO.of(false, "인증번호를 확인해주세요.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(apiResponseDTO);
    }
}

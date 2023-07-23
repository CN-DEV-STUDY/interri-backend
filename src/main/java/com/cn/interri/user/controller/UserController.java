package com.cn.interri.user.controller;

import com.cn.interri.common.dto.ResponseDto;
import com.cn.interri.design.inquiry.dto.ReqRegistrationResource;
import com.cn.interri.user.dto.UserSignUpRequest;
import com.cn.interri.user.service.RegisterService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final RegisterService registerService;

    // 이메일 전송
    @PostMapping("/cert")
    public void certificationEmail(@RequestParam(value = "email") String userEmail) throws MessagingException {
        registerService.certEmail(userEmail);
    }

    @GetMapping("/cert/ok")
    public ResponseEntity<String> passedCertEmail(@RequestParam(value = "email") String userEmail) {
        // 사용자를 enabledYn = N으로 넣고 추후 회원가입이 완료되면 enabledYn=Y로 변경하기

        registerService.passedCertEmail(userEmail);
        return ResponseEntity.ok()
                .body("이메일 인증이 완료되었습니다.");
    }

    /**
     * 이메일 인증 버튼을 클릭한 순간부터 5초간격으로 해당 api를 호출한다.
     * 이메일 인증이 완료 됐다면 해당 api는 호출되지 않는다.
     */
    @GetMapping("/cert/status/check")
    public ResponseEntity<ResponseDto<Boolean>> checkEmailCertStatus(@RequestParam(value = "email") String userEmail) throws Exception {
        boolean status = registerService.checkEmailCertStatus(userEmail);
        return ResponseEntity.ok()
                .body(ResponseDto.<Boolean>builder()
                        .data(status)
                        .build());
    }

    @PostMapping("/signUp")
    public void signUp(@RequestBody UserSignUpRequest request){
        registerService.signUp(request);
    }
}

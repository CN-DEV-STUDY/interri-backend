package com.cn.interri.user.controller;

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
                .body("ok");
    }

    @PostMapping("/signUp")
    public void signUp(@RequestBody UserSignUpRequest request){
        registerService.signUp(request);
    }
}

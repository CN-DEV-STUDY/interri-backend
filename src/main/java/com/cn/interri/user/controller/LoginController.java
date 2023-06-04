package com.cn.interri.user.controller;

import com.cn.interri.common.dto.ResponseDto;
import com.cn.interri.common.dto.TokenDto;
import com.cn.interri.user.dto.UserLoginRequest;
import com.cn.interri.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ResponseDto<TokenDto>> login(@RequestBody UserLoginRequest request) {
        // TODO: 파라미터 유효성 검증
        String email = request.getEmail();
        String password = request.getPassword();
        TokenDto tokenDto = authService.login(email, password);

        return ResponseEntity.ok()
                .body(ResponseDto.<TokenDto>builder()
                        .data(tokenDto)
                        .build());
    }
}


package com.cn.interri.user.service.impl;

import com.cn.interri.common.service.EmailService;
import com.cn.interri.user.dto.UserSignUpRequest;
import com.cn.interri.user.entity.User.User;
import com.cn.interri.user.enums.SignType;
import com.cn.interri.user.repository.UserRepository;
import com.cn.interri.user.service.RegisterService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final UserRepository userRepository;
    private final EmailService emailService;

    @Override
    public void signUp(UserSignUpRequest request) {
        User user = userRepository.save(User.dtoToUser(request));
        user.changeEnabled("Y");
    }

    @Override
    public void certEmail(String userEmail) throws MessagingException {

        Context context = new Context();
        context.setVariable("verificationLink", "http://localhost:8080/user/cert/ok?email=" + userEmail);
        emailService.sendEmail(userEmail, "이메일 인증", "email-template", context);

        userRepository.save(
                User.builder()
                        .email(userEmail)
                        .nickname("temp")
                        .password("1111")
                        .enableYn("N")
                        .signType(SignType.I)
                        .build()
        );
    }

    @Override
    public void passedCertEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다."));
        user.changeEnabled("C");
    }

    @Override
    public boolean checkEmailCertStatus(String email) throws Exception{
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다."));

        if (!user.getEnableYn().equals("C")){
            return false;
        } else {
            return true;
        }
    }
}


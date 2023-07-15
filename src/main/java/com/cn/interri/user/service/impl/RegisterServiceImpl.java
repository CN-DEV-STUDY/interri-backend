package com.cn.interri.user.service.impl;

import com.cn.interri.common.service.EmailService;
import com.cn.interri.user.dto.UserSignUpRequest;
import com.cn.interri.user.entity.User.User;
import com.cn.interri.user.repository.UserRepository;
import com.cn.interri.user.service.RegisterService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final UserRepository userRepository;
    private final EmailService emailService;

    @Override
    public void signUp(UserSignUpRequest request) {
        userRepository.save(User.dtoToUser(request));
    }

    @Override
    public void certEmail(String userEmail) throws MessagingException {

        Context context = new Context();
        context.setVariable("verificationLink", "http://localhost:8080/user/cert/ok");
        emailService.sendEmail(userEmail, "이메일 인증", "email-template", context);
    }
}


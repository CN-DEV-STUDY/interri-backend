package com.cn.interri.user.service;

import com.cn.interri.common.exception.exceptions.AlreadyCertEmailException;
import com.cn.interri.user.dto.UserSignUpRequest;
import jakarta.mail.MessagingException;

public interface RegisterService {
    void signUp(UserSignUpRequest request);

    void certEmail(String userEmail) throws MessagingException, AlreadyCertEmailException;

    void passedCertEmail(String userEmail) ;

    boolean checkEmailCertStatus(String email) throws Exception;
}

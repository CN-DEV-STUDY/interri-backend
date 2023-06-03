package com.cn.interri.config.security;

import com.cn.interri.exception.ErrorResponse;
import com.cn.interri.exception.enums.CommonErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Component("customAuthenticationEntryPoint")
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint{

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws ServletException, IOException {

        ErrorResponse res = new ErrorResponse(
                CommonErrorCode.INTERNAL_SERVER_ERROR.getHttpStatus(),
                CommonErrorCode.INTERNAL_SERVER_ERROR.getMessage()
        );

        OutputStream responseStream = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(responseStream, res);
        responseStream.flush();
    }
}

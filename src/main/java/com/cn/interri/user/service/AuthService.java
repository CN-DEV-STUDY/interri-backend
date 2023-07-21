package com.cn.interri.user.service;

import com.cn.interri.common.dto.TokenDto;

public interface AuthService {

    TokenDto login(String email, String password);
}

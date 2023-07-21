package com.cn.interri.common.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class TokenDto {
    private String grantType;
    private String accessToken;
    private String refreshToken;
}

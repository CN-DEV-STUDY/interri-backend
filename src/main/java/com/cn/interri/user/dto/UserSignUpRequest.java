package com.cn.interri.user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class UserSignUpRequest {
    @NotNull
    @Length(max = 20)
    private String email;

    @NotNull
    @Length(max = 20)
    private String nickname;

    @NotNull
    @Length(max = 20)
    private String password;

    @NotNull
    @Length(max = 20)
    private String signType;

    @Length(max = 50)
    private String address;

    @NotNull
    @Length(max = 20)
    private String phone;

    @Length(max = 100)
    private String profileImgNm;

    @Length(max = 100)
    private String profileImgPath;
}

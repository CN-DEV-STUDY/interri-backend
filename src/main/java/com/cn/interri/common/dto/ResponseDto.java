package com.cn.interri.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto<T> {
    private T data;
    private String message = "통신 성공!";

    @Builder
    public ResponseDto(T data) {
        this.data = data;
    }

    @Builder
    public ResponseDto(T data, String message) {
        this.data = data;
        this.message = message;
    }
}

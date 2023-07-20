package com.cn.interri.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomException extends Exception {

    private final ErrorCode errorCode;

}

package com.cn.interri.common.exception.exceptions;

import com.cn.interri.common.exception.CustomException;
import com.cn.interri.common.exception.ErrorCode;

public class AlreadyCertEmailException extends CustomException {
    public AlreadyCertEmailException(ErrorCode errorCode) {
        super(errorCode);
    }
}

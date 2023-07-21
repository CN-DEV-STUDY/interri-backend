package com.cn.interri.common.exception.exceptions;

import com.cn.interri.common.exception.CustomException;
import com.cn.interri.common.exception.ErrorCode;

public class EmptyFileException extends CustomException {


    public EmptyFileException(ErrorCode errorCode) {
        super(errorCode);
    }
}

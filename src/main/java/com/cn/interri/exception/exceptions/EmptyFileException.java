package com.cn.interri.exception.exceptions;

import com.cn.interri.exception.CustomException;
import com.cn.interri.exception.ErrorCode;
import com.cn.interri.exception.enums.CommonErrorCode;

public class EmptyFileException extends CustomException {


    public EmptyFileException(ErrorCode errorCode) {
        super(errorCode);
    }
}

package com.cn.interri.common.exception.exceptions;

import com.cn.interri.common.exception.CustomException;
import com.cn.interri.common.exception.ErrorCode;

public class FileExtNotFoundException extends CustomException {
    public FileExtNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}

package com.cn.interri.exception.exceptions;

import com.cn.interri.exception.CustomException;
import com.cn.interri.exception.ErrorCode;

public class FileExtNotFoundException extends CustomException {
    public FileExtNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}

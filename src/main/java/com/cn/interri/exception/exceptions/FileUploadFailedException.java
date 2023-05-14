package com.cn.interri.exception.exceptions;

import com.cn.interri.exception.CustomException;
import com.cn.interri.exception.ErrorCode;

public class FileUploadFailedException extends CustomException {
    public FileUploadFailedException(ErrorCode errorCode) {
        super(errorCode);
    }
}

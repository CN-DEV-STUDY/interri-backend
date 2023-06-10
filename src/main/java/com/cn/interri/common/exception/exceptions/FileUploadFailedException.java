package com.cn.interri.common.exception.exceptions;

import com.cn.interri.common.exception.CustomException;
import com.cn.interri.common.exception.ErrorCode;

public class FileUploadFailedException extends CustomException {
    public FileUploadFailedException(ErrorCode errorCode) {
        super(errorCode);
    }
}

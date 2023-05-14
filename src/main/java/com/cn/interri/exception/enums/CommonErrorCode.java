package com.cn.interri.exception.enums;

import com.cn.interri.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode {

    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "Invalid parameter included"),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "Resource not exists"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server Error"),
    EMPTY_FILE_EXCEPTION(HttpStatus.BAD_REQUEST, "File cannot be empty"),
    FILE_UPLOAD_FAILED_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "File upload failed"),
    FILE_SIZE_EXCEED(HttpStatus.BAD_REQUEST, "File size exceeded"),
    FILE_EXT_NOT_FOUND(HttpStatus.NOT_FOUND , "File ext not exists")
    ;

    private final HttpStatus httpStatus;
    private final String message;
}

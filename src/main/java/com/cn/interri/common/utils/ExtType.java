package com.cn.interri.common.utils;
import com.cn.interri.common.exception.enums.CommonErrorCode;
import com.cn.interri.common.exception.exceptions.FileExtNotFoundException;
import lombok.Getter;

import java.util.Arrays;

/**
 * 파일 확장자 검증
 */
@Getter
public enum ExtType {
    CASE1("JPG"),
    CASE2("JPEG"),
    CASE3("PNG");

    private String fileExt;

    ExtType(String fileExt) {
        this.fileExt = fileExt;
    }

    public static ExtType find(String fileExt){
        return Arrays.stream(values())
                .filter(e -> e.fileExt.equals(fileExt))
                .findAny().orElseThrow(()->new FileExtNotFoundException(CommonErrorCode.FILE_EXT_NOT_FOUND));
    }
}

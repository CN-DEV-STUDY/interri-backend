package com.cn.interri.common.utils;

import java.util.UUID;

public class FileUtils {

    public static final String REQUEST_PATH = "request/";

    /**
     * UUID 파일 이름 조회
     */
    public static String getUuidFileName (String fileName){

        String fileExt = getExt(fileName);

        UUID uuid = UUID.randomUUID();
        return uuid.toString() + fileExt;
    }

    /**
     * 파일 확장자 추출
     */
    public static String getExt(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".")+1);
    }



}

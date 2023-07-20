package com.cn.interri.common.service;

import com.cn.interri.common.exception.exceptions.EmptyFileException;
import com.cn.interri.common.exception.exceptions.FileUploadFailedException;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    String uploadFile(MultipartFile multipartFile,String purpose) throws FileUploadFailedException, EmptyFileException;
    void deleteFile();
}

package com.cn.interri.common.service.impl;

import com.amazonaws.services.s3.AmazonS3Client;
import com.cn.interri.common.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class S3FileServiceImpl implements FileService {

    private final AmazonS3Client amazonS3Client;

    @Value("{cloud.aws.s3.bucket}")
    private String bucketName;
    @Override
    public void uploadFile(MultipartFile multipartFile) {
        validateFileExists(multipartFile);

    }

    @Override
    public void deleteFile() {

    }

    private void validateFileExists(MultipartFile multipartFile) {
        if(multipartFile.isEmpty()) {
            throw new EmptyFileException();
        }
    }
}

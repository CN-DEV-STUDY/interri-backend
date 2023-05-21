package com.cn.interri.common.service.impl;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.cn.interri.common.service.FileService;
import com.cn.interri.exception.exceptions.EmptyFileException;
import com.cn.interri.exception.exceptions.FileUploadFailedException;
import com.cn.interri.exception.enums.CommonErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class S3FileServiceImpl implements FileService {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    /**
     * https://www.sunny-son.space/spring/Springboot%EB%A1%9C%20S3%20%ED%8C%8C%EC%9D%BC%20%EC%97%85%EB%A1%9C%EB%93%9C/
     * https://wakestand.tistory.com/300
     */
    @Override
    public String uploadFile(MultipartFile multipartFile) {
        // 1. 파일 유효성 검사
        validateFileExists(multipartFile);

        String fileName = multipartFile.getOriginalFilename();

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());
        objectMetadata.setContentLength(multipartFile.getSize());

        try (InputStream inputStream = multipartFile.getInputStream()) {
            amazonS3Client.putObject(new PutObjectRequest(bucketName, fileName, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            throw new FileUploadFailedException(CommonErrorCode.FILE_UPLOAD_FAILED_EXCEPTION);
        }

        return amazonS3Client.getUrl(bucketName, fileName).toString();
    }

    @Override
    public void deleteFile() {

    }

    private void validateFileExists(MultipartFile multipartFile) {
        if(multipartFile.isEmpty()) {
            throw new EmptyFileException(CommonErrorCode.EMPTY_FILE_EXCEPTION);
        }
    }
}

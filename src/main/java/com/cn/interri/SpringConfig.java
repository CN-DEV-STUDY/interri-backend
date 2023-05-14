package com.cn.interri;

import com.cn.interri.common.service.FileService;
import com.cn.interri.common.service.impl.LocalFileService;
import com.cn.interri.common.service.impl.S3FileServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public FileService fileService() {
        return new S3FileServiceImpl();
//        return new LocalFileService();
    }
}

package com.cn.interri.design.request.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 디자인 요청 등록 페이지에서 사진, 공간, 설명에 대한 값을 받는 Dto
 */
@Getter
@Setter
public class RegistReqDtos {
    private List<MultipartFile> multipartFiles; // 사진
    private int roomTypeId; // 공간
    private String content; // 사진 설명
}

package com.cn.interri.design.request.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

/**
 * 디자인 요청 등록 페이지에서 사진, 공간, 설명에 대한 값을 받는 Dto
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DesignRequestInfo {
    private String content; // 사진 설명
    private MultipartFile image; // 사진
    private String roomTypeId; // 공간
}

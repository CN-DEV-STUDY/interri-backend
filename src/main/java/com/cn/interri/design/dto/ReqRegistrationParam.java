package com.cn.interri.design.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ReqRegistrationParam {
    private long userId; // 유저 아이디
    private long sizeId; // 평수
    private long housingTypeId; // 주거 형태
    private String mainColor; // 메인 컬러
    private String subColor; // 서브 컬러
    private long maxPrice; // 최대 가격
    private LocalDate dueDate; // 마감 기한
    private long styleId; // 스타일
    private List<MultipartFile> multipartFiles; // 사진
    private List<Integer> roomTypes; // 공간
    private List<String> content; // 사진 설명
}

package com.cn.interri.design.inquiry.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistReqDto {
    private String sizeId; // 평수
    private String housingTypeId; // 주거 형태
    private String mainColor; // 메인 컬러
    private String subColor; // 서브 컬러
    private int maxPrice; // 최대 가격
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDate dueDate; // 마감 기한
    private String[] styleId; // 스타일
    private String tempYn;

    private List<DesignRequestInfo> designRequestInfos;
}

package com.cn.interri.design.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ReqRegistrationParam {
    private long userId; // 유저 아이디
    private int sizeId; // 평수
    private int housingTypeId; // 주거 형태
    private String mainColor; // 메인 컬러
    private String subColor; // 서브 컬러
    private int maxPrice; // 최대 가격
    private LocalDate dueDate; // 마감 기한
    private int styleId; // 스타일
    private String tempYn;

    private List<ReqRegistrationDto> reqRegistrationDtoList;
}

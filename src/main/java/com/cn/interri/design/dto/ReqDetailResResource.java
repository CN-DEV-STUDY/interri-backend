package com.cn.interri.design.dto;

import lombok.Value;

import java.time.LocalDate;

/**
 * design_req_detail 디자인 응답 response dto
 */
@Value
public class ReqDetailResResource {

    // 업체 또는 개인 닉네임
    String nickname;

    // 업체 또는 개인 프로필 이미지 파일 이름
    String profileImgNm;

    // 업체 또는 개인 프로필 이미지 저장 경로
    String profileImgPath;

    // 응답 가격
    int price;

    // 채택수
    int adoptionCnt;

    // 대표 이미지 저장 경로
    String repImgPath;
}

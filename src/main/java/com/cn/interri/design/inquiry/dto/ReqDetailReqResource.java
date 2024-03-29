package com.cn.interri.design.inquiry.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

/**
 * design_req_detail 디자인 요청 response dto
 */
@Getter
@Setter
public class ReqDetailReqResource {

    // 디자인 요청 id
    Long id;

    // 사용자 아이디
    String userId;

    // 평수
    String sizeNm;

    // 주거 형태
    String housingTypeNm;

    // 스타일
    String styleNm;

    // 메인 컬러
    String mainColor;

    // 서브 컬러
    String subColor;

    // 최대 가격
    int maxPrice;

    // 마감 기한
    LocalDate dueDate;

    // 조회수
    int viewCnt;

    // 스크랩수
    int scrabCnt;

    // 디자인 요청 정보 목록
    List<ReqInfoDetailResource> reqInfoDetailResources;

    // 디자인 요청 응답 목록
    List<ReqDetailResResource> reqDetailResResources;
}

package com.cn.interri.design.inquiry.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * design_req_detail 이미지 dto
 */
@Getter
@Setter
public class ReqInfoDetailResource {

    Long infoId;

    // 공간
    String roomTypeNm;

    // 이미지 저장 경로
    String imgPathList;

    // 사진 설명
    String content;
}

package com.cn.interri.design.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

/**
 * design_req_detail 이미지 dto
 */
@Getter
@Setter
public class ReqInfoDetailResource {

    // 공간
    String roomTypeNm;

    // 이미지 저장 경로
    String imgPath;

    // 사진 설명
    String content;
}

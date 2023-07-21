package com.cn.interri.design.reply.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReplyInfoDetailResource {
    Long infoId;

    // TODO 공간

    // 이미지 저장 경로
    List<String> imgPathList;

    // 사진 설명
    String content;
}

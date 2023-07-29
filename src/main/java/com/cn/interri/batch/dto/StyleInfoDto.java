package com.cn.interri.batch.dto;

import java.io.Serializable;

/**
 * 인테리어 트렌드 카드에 들어가는 정보
 */
public record StyleInfoDto(
        Long designReqId,    // designReqId
        String imgPath,      // 파일 경로
        Integer maxPrice,    // 가격
        String nickname,     // 사용자 닉네임
        Integer scrabCnt,    // 스크랩 수
        Integer viewCnt      // 조회수

) implements Serializable {}

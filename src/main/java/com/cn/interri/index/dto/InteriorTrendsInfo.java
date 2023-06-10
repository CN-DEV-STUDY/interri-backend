package com.cn.interri.index.dto;

/**
 * 인테리어 트렌드 카드에 들어가는 정보
 * @param filePath
 * @param maxPrice
 * @param nickname
 * @param scrabCnt
 * @param viewCnt
 */
public record InteriorTrendsInfo(
        String filePath,     // 파일 경로
        Integer maxPrice,    // 가격
        String nickname,     // 사용자 닉네임
        Integer scrabCnt,    // 스크랩 수
        Integer viewCnt      // 조회수

) {}

package com.cn.interri.batch.dto;

/**
 * 주간 랭킹 dto
 */
public record WeeklyRankingDto (
        Long userId,
        String profileImgPath,
        String nickname,
        Integer adoptionCnt     // 채택수

) {}

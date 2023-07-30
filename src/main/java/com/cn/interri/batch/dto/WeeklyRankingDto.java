package com.cn.interri.batch.dto;

import com.cn.interri.user.entity.User.User;
import lombok.Builder;

import java.util.List;
import java.util.Map;

/**
 * 주간 랭킹 to
 */
@Builder
public record WeeklyRankingDto (
        Long userId,
        String profileImgPath,
        String nickname,
        Integer adoptionCnt     // 채택수
) {

    public static List<WeeklyRankingDto> toWeeklyRankingDto(List<User> users, Map<Long, Integer> userIdAdoptionCntMap) {
        return users.stream()
                .map(user -> WeeklyRankingDto.builder()
                        .userId(user.getId())
                        .profileImgPath(user.getProfileImgPath())
                        .nickname(user.getNickname())
                        .adoptionCnt(userIdAdoptionCntMap.get(user.getId()))
                        .build())
                .sorted((o1, o2) -> o1.adoptionCnt)
                .toList();
    }
}

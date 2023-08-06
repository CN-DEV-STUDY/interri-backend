package com.cn.interri.user.repository.custom;

import com.cn.interri.batch.dto.WeeklyRankingDto;

import java.util.List;

public interface UserCustomRepository {
    List<WeeklyRankingDto> getWeeklyRanking();
}

package com.cn.interri.user.repository.custom.impl;

import com.cn.interri.batch.dto.WeeklyRankingDto;
import com.cn.interri.design.reply.entity.DesignReply;
import com.cn.interri.user.entity.User.User;
import com.cn.interri.user.repository.custom.UserCustomRepository;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.cn.interri.batch.dto.WeeklyRankingDto.*;
import static com.cn.interri.batch.entity.QAdoptionHistory.adoptionHistory;
import static com.cn.interri.design.reply.entity.QDesignReply.designReply;
import static com.cn.interri.user.entity.User.QUser.user;

@Repository
@RequiredArgsConstructor
public class UserCustomRepositoryImpl implements UserCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<WeeklyRankingDto> getWeeklyRanking() {

        NumberPath<Long> aliasCount = Expressions.numberPath(Long.class, "count");
        List<Tuple> tupleList = queryFactory.select(adoptionHistory, adoptionHistory.id.count().as(aliasCount))
                .from(adoptionHistory)
                .where(adoptionHistory.regDate.between(LocalDateTime.now().minusWeeks(1), LocalDateTime.now()))
                .groupBy(adoptionHistory.id)
                .orderBy(aliasCount.desc())
                .limit(10)
                .fetch();

        //
        List<Long> designReplyIds = tupleList.stream()
                .map(tuple -> tuple.get(adoptionHistory.designReply))
//                .filter(Objects::nonNull)
                .map(DesignReply::getId)
                .toList();

        List<User> users = queryFactory.select(user)
                .from(designReply)
                .join(designReply.user, user)
                .join(designReply.adoptionHistories, adoptionHistory).fetchJoin()
                .where(adoptionHistory.designReply.id.in(designReplyIds))
                .limit(10)
                .fetch();

        Map<Long, Integer> userIdAdoptionCntMap = tupleList.stream()
                .collect(Collectors.toMap(
                        tuple -> tuple.get(adoptionHistory.designReply.user.id),
                        tuple -> tuple.get(aliasCount.intValue())
                ));

        List<WeeklyRankingDto> weeklyRankingDtos = toWeeklyRankingDto(users, userIdAdoptionCntMap);


        return weeklyRankingDtos;
    }
}

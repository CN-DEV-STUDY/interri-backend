package com.cn.interri.design.inquiry.repository.custom.impl;

import com.cn.interri.batch.dto.InteriorTrendDto;
import com.cn.interri.batch.dto.StyleInfoDto;
import com.cn.interri.common.enums.CodeType;
import com.cn.interri.design.inquiry.dto.ReqDetailReqResource;
import com.cn.interri.design.inquiry.repository.custom.DesignReqCustomRepository;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.cn.interri.common.entity.QCommonCode.commonCode;
import static com.cn.interri.common.entity.QCommonCodeDesign.commonCodeDesign;
import static com.cn.interri.design.inquiry.entity.QDesignReq.designReq;
import static com.cn.interri.design.inquiry.entity.QDesignReqInfo.designReqInfo;
import static com.cn.interri.design.inquiry.entity.QFileDesignReq.fileDesignReq;
import static com.cn.interri.user.entity.User.QUser.user;

@Repository
@RequiredArgsConstructor
public class DesignReqCustomRepositoryImpl implements DesignReqCustomRepository {

    private final JPAQueryFactory queryFactory;


    @Override
    public ReqDetailReqResource getReqDetail(Long id) {

        return queryFactory
                .select(Projections.fields(ReqDetailReqResource.class,
                        designReq.id,
                        designReq.mainColor,
                        designReq.maxPrice,
                        designReq.subColor,
                        user.nickname.as("userId"),
                        getCode(id, "ST%", "styleNm"),
                        getCode(id, "S_", "sizeNm"),
                        getCode(id, "HT%", "housingTypeNm"),
                        designReq.dueDate,
                        designReq.viewCnt,
                        designReq.scrabCnt
                ))
                .from(designReq)
                .leftJoin(designReq.user, user)
                .where(designReq.id.eq(id))
                .fetchOne();
    }

    /**
     * 조회된 스타일(총 2개) 당 최대 10개의 데이터를 보여준다.
     */
    @Override
    public List<InteriorTrendDto> getWeekTrends() {

        LocalDateTime startDateTime = LocalDateTime.now().minusWeeks(1);
        LocalDateTime endDateTime = LocalDateTime.now();

        DateTimeExpression<LocalDateTime> startDateTimeExpression = Expressions.dateTimeTemplate(
            LocalDateTime.class, "{0}", startDateTime
        );
        DateTimeExpression<LocalDateTime> endDateTimeExpression = Expressions.dateTimeTemplate(
            LocalDateTime.class, "{0}", endDateTime
        );

        NumberPath<Long> aliasCount = Expressions.numberPath(Long.class, "count");
        List<Tuple> tupleList = queryFactory
                .select(commonCode, commonCode.id.count().as(aliasCount))
                .from(designReq)
                .join(designReq.commonCodeDesigns, commonCodeDesign)
                .join(commonCodeDesign.commonCode, commonCode)
                .where(
                        commonCode.codeType.eq(CodeType.STYLE),
                        designReq.regDate.between(LocalDateTime.now().minusWeeks(1), LocalDateTime.now())
                )
                .groupBy(commonCode.id)
                .orderBy(aliasCount.desc())
                .limit(2)
                .join(designReq.commonCodeDesigns, commonCodeDesign)
                .join(commonCodeDesign.commonCode, commonCode)
                .where(
                        commonCode.codeType.eq(CodeType.STYLE),
                        designReq.regDate.between(startDateTimeExpression, endDateTimeExpression)
                )
                .groupBy(commonCode.id)
                .orderBy(aliasCount.desc())
                .limit(2)
                .fetch();


        final Integer TRENDS_FETCH_SIZE = 10;
        List<InteriorTrendDto> responses = new ArrayList<>();
        for(Tuple tuple : tupleList) {
            List<StyleInfoDto> styleInfos = queryFactory
                    .select(Projections.constructor(StyleInfoDto.class,
                            designReq.id,
                            fileDesignReq.filePath,
                            designReq.maxPrice,
                            user.nickname,
                            designReq.scrabCnt,
                            designReq.viewCnt
                    ))
                    .from(designReq)
                        .leftJoin(designReq.user, user)
                        .leftJoin(designReq.designReqInfoList, designReqInfo)
                        .leftJoin(designReq.commonCodeDesigns, commonCodeDesign)
                        .leftJoin(commonCodeDesign.commonCode, commonCode)
                        .leftJoin(designReqInfo.fileDesignReq, fileDesignReq)
                    // TODO: for문 지우고 in절로 변경하기
                    .where(commonCode.id.eq(tuple.get(commonCode.id)), fileDesignReq.delYn.eq("N"), user.enableYn.eq("Y"), designReq.delYn.eq("N"), designReqInfo.delYn.eq("N"))
                    .orderBy(designReq.scrabCnt.desc())
                    .limit(TRENDS_FETCH_SIZE)
                    .fetch();

            InteriorTrendDto response = InteriorTrendDto.builder()
                    .styleId(tuple.get(commonCode.id))
                    .styleName(tuple.get(commonCode.codeNm))
                    .styleInfos(styleInfos)
                    .build();
            responses.add(response);
        }

        return responses;
    }

/*    private Expression<String> getCode(Long id, String query, String alias) {
        return ExpressionUtils.as(
                JPAExpressions
                        .select(commonCode.codeNm)
                        .from(commonCode)
                        .leftJoin(commonCodeDesign).on(commonCode.id.eq(commonCodeDesign.commonCode.id))
                        .innerJoin(designReq).on(commonCodeDesign.designReq.id.eq(designReq.id))
                        .where(designReq.id.eq(id).and(commonCode.id.like(query))),
                alias
        );
    }*/

    private Expression<String> getCode(Long id, String query, String alias) {
        return ExpressionUtils.as(
                JPAExpressions
                        .select(Expressions.stringTemplate("group_concat({0})", commonCode.codeNm))
                        .from(commonCode)
                        .where(commonCode.id.in(
                                JPAExpressions
                                        .select(commonCodeDesign.commonCode.id)
                                        .from(commonCodeDesign)
                                        .where(commonCodeDesign.designReq.id.eq(id).and(commonCode.id.like(query)))
                        ))
                        .groupBy(commonCode.codeType),
                alias
        );
    }

}

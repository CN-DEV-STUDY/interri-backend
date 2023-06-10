package com.cn.interri.design.request.repository.custom.impl;

import com.cn.interri.design.request.dto.ReqDetailReqResource;
import com.cn.interri.design.request.repository.custom.DesignReqCustomRepository;
import com.cn.interri.common.entity.Style;
import com.cn.interri.index.dto.InteriorTrendsInfo;
import com.cn.interri.index.dto.InteriorTrendsResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.cn.interri.common.entity.QHousingType.housingType;
import static com.cn.interri.common.entity.QStyle.style;
import static com.cn.interri.common.entity.QSize.size;
import static com.cn.interri.design.request.entity.QDesignReq.designReq;
import static com.cn.interri.design.request.entity.QDesignReqInfo.designReqInfo;
import static com.cn.interri.design.request.entity.QFileDesignReq.fileDesignReq;
import static com.cn.interri.user.entity.User.QUser.*;

@Repository
@RequiredArgsConstructor
public class DesignReqCustomRepositoryImpl implements DesignReqCustomRepository {

    private final JPAQueryFactory queryFactory;


    @Override
    public ReqDetailReqResource getReqDetail(Long id) {
        return queryFactory
                .select(Projections.fields(ReqDetailReqResource.class,
                        designReq.id,
                        user.nickname.as("userId"),
                        size.sizeNm,
                        housingType.housingTypeNm,
                        style.styleNm,
                        designReq.mainColor,
                        designReq.subColor,
                        designReq.maxPrice,
                        designReq.dueDate,
                        designReq.viewCnt,
                        designReq.scrabCnt
                ))
                .from(designReq)
                .leftJoin(designReq.user, user)
                .leftJoin(designReq.size, size)
                .leftJoin(designReq.housingType, housingType)
                .leftJoin(designReq.style, style)
                .where(designReq.id.eq(id))
                .fetchOne();
    }

    /**
     * 인테리어 트렌드 조회
     *
     * @return
     */
    @Override
    public List<InteriorTrendsResponse> getTrends(List<Style> styles) {
        final Integer TRENDS_FETCH_SIZE = 10;
        List<InteriorTrendsResponse> responses = new ArrayList<>();

        for(Style style : styles) {
            List<InteriorTrendsInfo> interiorTrendsInfos = queryFactory
                    .select(Projections.constructor(InteriorTrendsInfo.class,
                            fileDesignReq.filePath,
                            designReq.maxPrice,
                            user.nickname,
                            designReq.scrabCnt,
                            designReq.viewCnt
                    ))
                    .from(designReq)
                    .leftJoin(designReq.user, user).on(user.enableYn.eq("Y"))
                    .leftJoin(designReq.designReqInfoList, designReqInfo).on(designReq.delYn.eq("N"))
                    .leftJoin(designReqInfo.fileDesignReqList, fileDesignReq).on(designReqInfo.delYn.eq("N"))
                    .where(fileDesignReq.delYn.eq("N"), styleIn(styles))
                    .limit(TRENDS_FETCH_SIZE)
                    .fetch();

            InteriorTrendsResponse response = new InteriorTrendsResponse(style.getId(), style.getStyleNm(), interiorTrendsInfos);
            responses.add(response);
        }

        return responses;
    }

    private BooleanExpression styleIn(List<Style> styles) {
        return designReq.style.id.in(styles.stream()
                .map(style -> style.getId())
                .toList());
    }
}

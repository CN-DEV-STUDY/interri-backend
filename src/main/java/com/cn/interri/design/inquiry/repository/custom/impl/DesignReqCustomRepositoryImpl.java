package com.cn.interri.design.inquiry.repository.custom.impl;

import com.cn.interri.common.entity.CommonCode;
import com.cn.interri.design.inquiry.dto.ReqDetailReqResource;
import com.cn.interri.design.inquiry.repository.custom.DesignReqCustomRepository;
import com.cn.interri.index.dto.InteriorTrendsDto;
import com.cn.interri.index.dto.StyleInfo;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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
                        user.nickname.as("userId"),
                        designReq.mainColor,
                        designReq.subColor,
                        designReq.maxPrice,
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
     * 인테리어 트렌드 조회
     */
    @Override
    public List<InteriorTrendsDto> getTrends(List<CommonCode> commonCodes) {
        final Integer TRENDS_FETCH_SIZE = 10;
        List<InteriorTrendsDto> responses = new ArrayList<>();

        for(CommonCode commonCode : commonCodes) {
            List<StyleInfo> styleInfos = queryFactory
                    .select(Projections.constructor(StyleInfo.class,
                            designReq.id,
                            fileDesignReq.filePath,
                            designReq.maxPrice,
                            user.nickname,
                            designReq.scrabCnt,
                            designReq.viewCnt
                    ))
                    .from(designReq)
                    .leftJoin(designReq.user, user).on(user.enableYn.eq("Y"))
                    .leftJoin(designReq.designReqInfoList, designReqInfo).on(designReq.delYn.eq("N"))
                    .leftJoin(designReqInfo.fileDesignReq, fileDesignReq).on(designReqInfo.delYn.eq("N"))
                    .where(fileDesignReq.delYn.eq("N"))
                    .limit(TRENDS_FETCH_SIZE)
                    .fetch();

            InteriorTrendsDto response = new InteriorTrendsDto(commonCode.getId(), commonCode.getCodeNm(), styleInfos);
            responses.add(response);
        }

        return responses;
    }

//    private BooleanExpression styleIn(List<Style> styles) {
//        return designReq.style.id.in(styles.stream()
//                .map(Style::getId)
//                .toList());
//    }
}

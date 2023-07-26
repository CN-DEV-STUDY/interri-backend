package com.cn.interri.design.inquiry.repository.custom.impl;

import com.cn.interri.common.entity.CommonCode;
import com.cn.interri.common.enums.CodeType;
import com.cn.interri.design.inquiry.dto.ReqDetailReqResource;
import com.cn.interri.design.inquiry.repository.custom.DesignReqCustomRepository;
import com.cn.interri.index.dto.InteriorTrendsDto;
import com.cn.interri.index.dto.StyleInfo;
import com.querydsl.core.types.Projections;
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
     * 조회된 스타일(총 2개) 당 최대 10개의 데이터를 보여준다.
     */
    @Override
    public List<InteriorTrendsDto> getWeekTrends() {

        List<CommonCode> commonCodes = queryFactory
                .select(commonCode)
                .from(designReq)
                    .join(designReq.commonCodeDesigns, commonCodeDesign)
                    .join(commonCodeDesign.commonCode, commonCode)
                .where(commonCode.codeType.eq(CodeType.STYLE), designReq.regDate.between(LocalDateTime.now().minusWeeks(1), LocalDateTime.now()))
                .fetch();

        final Integer TRENDS_FETCH_SIZE = 10;
        List<InteriorTrendsDto> responses = new ArrayList<>();
        for(CommonCode code : commonCodes) {
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
                        .leftJoin(designReq.user, user)
                        .leftJoin(designReq.designReqInfoList, designReqInfo)
                        .leftJoin(designReq.commonCodeDesigns, commonCodeDesign)
                        .leftJoin(commonCodeDesign.commonCode, commonCode)
                        .leftJoin(designReqInfo.fileDesignReq, fileDesignReq)
                    .where(commonCode.id.eq(code.getId()), fileDesignReq.delYn.eq("N"), user.enableYn.eq("Y"), designReq.delYn.eq("N"), designReqInfo.delYn.eq("N"))
                    .orderBy(designReq.scrabCnt.desc())
                    .limit(TRENDS_FETCH_SIZE)
                    .fetch();

            InteriorTrendsDto response = InteriorTrendsDto.builder()
                    .styleId(code.getId())
                    .styleName(code.getCodeNm())
                    .styleInfos(styleInfos)
                    .build();
            responses.add(response);
        }

        return responses;
    }
}

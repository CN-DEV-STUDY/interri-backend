package com.cn.interri.design.inquiry.repository.custom.impl;

import com.cn.interri.design.inquiry.dto.ReqInfoDetailResource;
import com.cn.interri.design.inquiry.repository.custom.DesignReqInfoCustomRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.cn.interri.design.inquiry.entity.QDesignReqInfo.designReqInfo;

@Repository
@RequiredArgsConstructor
public class DesignReqInfoCustomRepositoryImpl implements DesignReqInfoCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ReqInfoDetailResource> getReqInfoDetail(Long id) {
        return queryFactory
                .select(Projections.fields(ReqInfoDetailResource.class,
                        designReqInfo.id.as("infoId"),
//                        roomType.roomTypeNm.as("roomTypeNm"),
                        designReqInfo.content.as("content")
                        ))
                .from(designReqInfo)
//                .leftJoin(designReqInfo.roomType, roomType)
                .where(designReqInfo.designReq.id.eq(id))
                .fetch();
    }
}

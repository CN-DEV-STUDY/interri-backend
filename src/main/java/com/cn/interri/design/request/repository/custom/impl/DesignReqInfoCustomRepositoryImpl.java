package com.cn.interri.design.request.repository.custom.impl;

import com.cn.interri.design.request.dto.ReqInfoDetailResource;
import com.cn.interri.design.request.repository.custom.DesignReqInfoCustomRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.cn.interri.common.entity.QRoomType.roomType;
import static com.cn.interri.design.request.entity.QDesignReqInfo.designReqInfo;

@Repository
@RequiredArgsConstructor
public class DesignReqInfoCustomRepositoryImpl implements DesignReqInfoCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ReqInfoDetailResource> getReqInfoDetail(Long id) {
        return queryFactory
                .select(Projections.fields(ReqInfoDetailResource.class,
                        designReqInfo.id.as("infoId"),
                        roomType.roomTypeNm.as("roomTypeNm"),
                        designReqInfo.content.as("content")
                        ))
                .from(designReqInfo)
                .leftJoin(designReqInfo.roomType, roomType)
                .where(designReqInfo.designReq.id.eq(id))
                .fetch();
    }
}

package com.cn.interri.design.repository.custom.impl;

import com.cn.interri.design.domain.QDesignReq;
import com.cn.interri.design.dto.ReqInfoDetailResource;
import com.cn.interri.design.repository.custom.DesignReqInfoCustomRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.cn.interri.design.domain.QDesignReq.designReq;
import static com.cn.interri.design.domain.QDesignReqInfo.designReqInfo;
import static com.cn.interri.design.domain.QFileDesignReq.fileDesignReq;
import static com.cn.interri.design.domain.QRoomType.roomType;

@Repository
@RequiredArgsConstructor
public class DesignReqInfoCustomRepositoryImpl implements DesignReqInfoCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ReqInfoDetailResource> getReqInfoDetail(Long id) {
        return queryFactory
                .select(Projections.fields(ReqInfoDetailResource.class,
                        roomType.roomTypeNm.as("roomTypeNm"),
                        fileDesignReq.filePath.as("imgPath"),
                        designReqInfo.content.as("content")
                        ))
                .from(designReqInfo)
                .leftJoin(designReqInfo.roomType, roomType)
                .leftJoin(designReqInfo.fileDesignReq , fileDesignReq)
                .where(designReqInfo.designReq.id.eq(id))
                .fetch();
    }
}

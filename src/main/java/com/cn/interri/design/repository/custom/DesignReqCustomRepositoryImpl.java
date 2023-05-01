package com.cn.interri.design.repository.custom;

import com.cn.interri.design.domain.QDesignReq;
import com.cn.interri.design.dto.ReqDetailReqResource;
import com.cn.interri.user.domain.User.QUser;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.cn.interri.design.domain.QDesignReq.designReq;
import static com.cn.interri.user.domain.User.QUser.*;

@Repository
@RequiredArgsConstructor
public class DesignReqCustomRepositoryImpl implements DesignReqCustomRepository{

    private final JPAQueryFactory queryFactory;


    @Override
    public ReqDetailReqResource getReqDetail() {
        queryFactory
                .select(Projections.fields(ReqDetailReqResource.class,
                        user.id.as("userId"),
                        designReq.mainColor,
                        designReq.subColor,
                        designReq.maxPrice,
                        designReq.dueDate
                        ))
                .from(designReq)
                .leftJoin(designReq.user, user)
                .leftJoin(designReq)
                .fetchOne();
        return null;
    }
}
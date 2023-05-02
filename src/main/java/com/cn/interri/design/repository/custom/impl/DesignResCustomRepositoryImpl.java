package com.cn.interri.design.repository.custom.impl;

import com.cn.interri.design.domain.QDesignReq;
import com.cn.interri.design.domain.QDesignRes;
import com.cn.interri.design.domain.QFileDesignRes;
import com.cn.interri.design.dto.ReqDetailResResource;
import com.cn.interri.design.repository.custom.DesignResCustomRepository;
import com.cn.interri.user.domain.User.QUser;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.cn.interri.design.domain.QDesignRes.designRes;
import static com.cn.interri.design.domain.QFileDesignRes.fileDesignRes;
import static com.cn.interri.user.domain.User.QUser.user;

@Repository
@RequiredArgsConstructor
public class DesignResCustomRepositoryImpl implements DesignResCustomRepository {
    private final JPAQueryFactory queryFactory;
    @Override
    public List<ReqDetailResResource> getReqDetailRes(Long id) {
        return queryFactory
                .select(Projections.fields(ReqDetailResResource.class,
                        designRes.id,
                        user.nickname,
                        user.profileImgNm,
                        user.profileImgPath,
                        designRes.price,
                        user.adoptionCnt
                        ))
                .from(designRes)
                .leftJoin(designRes.user, user)
                .where(designRes.designReqId.eq(id),designRes.delYn.eq("N"))
                .fetch();
    }
}

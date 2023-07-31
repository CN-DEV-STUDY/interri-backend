package com.cn.interri.design.reply.repository.custom.impl;

import com.cn.interri.design.inquiry.dto.ReqDetailResResource;
import com.cn.interri.design.reply.entity.QDesignReply;
import com.cn.interri.design.reply.repository.custom.DesignReplyCustomRepository;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.cn.interri.design.reply.entity.QDesignReply.designReply;
import static com.cn.interri.user.entity.User.QUser.user;

@Repository
@RequiredArgsConstructor
public class DesignReplyCustomRepositoryImpl implements DesignReplyCustomRepository {
    private final JPAQueryFactory queryFactory;
    @Override
    public List<ReqDetailResResource> getReqDetailRes(Long id, String sortType) {

        List<OrderSpecifier<?>> orderCond = new ArrayList<>();


        if(sortType == null || sortType.equals("new")){ // 정렬 조건 - 가격순
            OrderSpecifier<LocalDateTime> orderCond1 = designReply.regDate.desc();
            orderCond.add(orderCond1);
        } else if (sortType.equals("price")){ // 정렬 조건 - 최신순(default)
            OrderSpecifier<Integer> orderCond2 = designReply.price.asc();
            orderCond.add(orderCond2);
        }

        return queryFactory
                .select(Projections.fields(ReqDetailResResource.class,
                        designReply.id,
                        user.nickname,
                        user.profileImgNm,
                        user.profileImgPath,
                        designReply.price,
                        user.adoptionCnt,
                        designReply.viewCnt
                        ))
                .from(designReply)
                .leftJoin(designReply.user, user)
                .where(designReply.designReqId.eq(id),designReply.delYn.eq("N"))
                .orderBy(orderCond.toArray(new OrderSpecifier[0])) // OrderSpecifier[0]은 빈 배열을 생성하여 사용하며 이를 통해 orderCond 리스트가 비어있는 경우에도 정상적으로 동작
                .fetch();
    }
}

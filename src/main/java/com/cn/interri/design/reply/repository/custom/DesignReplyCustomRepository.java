package com.cn.interri.design.reply.repository.custom;

import com.cn.interri.design.inquiry.dto.ReqDetailResResource;

import java.util.List;

public interface DesignReplyCustomRepository {
    List<ReqDetailResResource> getReqDetailRes(Long id , String sortType);
}

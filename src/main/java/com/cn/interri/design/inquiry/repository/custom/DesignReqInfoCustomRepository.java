package com.cn.interri.design.inquiry.repository.custom;

import com.cn.interri.design.inquiry.dto.ReqInfoDetailResource;

import java.util.List;

public interface DesignReqInfoCustomRepository {
    List<ReqInfoDetailResource> getReqInfoDetail(Long id);
}

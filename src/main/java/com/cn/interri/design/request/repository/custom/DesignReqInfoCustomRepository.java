package com.cn.interri.design.request.repository.custom;

import com.cn.interri.design.request.dto.ReqInfoDetailResource;

import java.util.List;

public interface DesignReqInfoCustomRepository {
    List<ReqInfoDetailResource> getReqInfoDetail(Long id);
}

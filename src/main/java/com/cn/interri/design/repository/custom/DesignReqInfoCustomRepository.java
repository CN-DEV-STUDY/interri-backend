package com.cn.interri.design.repository.custom;

import com.cn.interri.design.dto.ReqInfoDetailResource;

import java.util.List;

public interface DesignReqInfoCustomRepository {
    List<ReqInfoDetailResource> getReqInfoDetail(Long id);
}

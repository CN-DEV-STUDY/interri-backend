package com.cn.interri.design.request.repository.custom;

import com.cn.interri.design.request.dto.ReqDetailResResource;

import java.util.List;

public interface DesignResCustomRepository {
    List<ReqDetailResResource> getReqDetailRes(Long id , String sortType);
}

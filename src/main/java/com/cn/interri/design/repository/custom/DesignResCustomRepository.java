package com.cn.interri.design.repository.custom;

import com.cn.interri.design.dto.ReqDetailResResource;

import java.util.List;

public interface DesignResCustomRepository {
    List<ReqDetailResResource> getReqDetailRes(Long id , String sortType);
}

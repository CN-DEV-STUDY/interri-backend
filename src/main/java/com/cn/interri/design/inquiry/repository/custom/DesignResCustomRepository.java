package com.cn.interri.design.inquiry.repository.custom;

import com.cn.interri.design.inquiry.dto.ReqDetailResResource;

import java.util.List;

public interface DesignResCustomRepository {
    List<ReqDetailResResource> getReqDetailRes(Long id , String sortType);
}

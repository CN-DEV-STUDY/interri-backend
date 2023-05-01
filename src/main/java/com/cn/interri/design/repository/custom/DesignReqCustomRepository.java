package com.cn.interri.design.repository.custom;

import com.cn.interri.design.dto.ReqDetailReqResource;

public interface DesignReqCustomRepository {
    ReqDetailReqResource getReqDetail(Long id);
}

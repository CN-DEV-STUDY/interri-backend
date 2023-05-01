package com.cn.interri.design.service;

import com.cn.interri.design.dto.ReqRegistrationResource;
import com.cn.interri.design.dto.ReqDetailReqResource;

public interface PageService {

    ReqRegistrationResource getRegistrationPageResource();

    ReqDetailReqResource getDesignReqDetails(Long id);
}

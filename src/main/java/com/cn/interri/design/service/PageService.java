package com.cn.interri.design.service;

import com.cn.interri.design.dto.RegistrationResource;
import com.cn.interri.design.dto.ReqDetailReqResource;

public interface PageService {

    RegistrationResource getRegistrationPageResource();

    ReqDetailReqResource getDesignReqDetails();
}

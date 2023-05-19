package com.cn.interri.design.service;

import com.cn.interri.design.dto.ReqRegistrationParam;

public interface RegisterDesignService {

    void saveDesignRequest(ReqRegistrationParam reqRegistrationParam) throws Exception;
    void saveDesignResponse();

}

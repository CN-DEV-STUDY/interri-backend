package com.cn.interri.design.request.service;

import com.cn.interri.design.request.dto.ReqRegistrationParam;
import com.cn.interri.design.request.dto.ResRegistrationParam;

public interface RegisterDesignService {

    void saveDesignRequest(ReqRegistrationParam reqRegistrationParam) throws Exception;
    void saveDesignResponse(long designReqId, ResRegistrationParam res) throws Exception;

}

package com.cn.interri.design.service;

import com.cn.interri.design.dto.ReqRegistrationParam;
import com.cn.interri.design.dto.ResRegistrationParam;

public interface RegisterDesignService {

    void saveDesignRequest(ReqRegistrationParam reqRegistrationParam);
    void saveDesignRequestTemp();
    void saveDesignResponse(Long designReqId, ResRegistrationParam res);

}

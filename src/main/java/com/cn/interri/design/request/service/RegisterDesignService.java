package com.cn.interri.design.request.service;

import com.cn.interri.design.request.dto.RegistReqDto;
import com.cn.interri.design.request.dto.ResRegistrationParam;

public interface RegisterDesignService {

    void saveDesignRequest(RegistReqDto reqRegistrationParam) throws Exception;
    void saveDesignResponse(long designReqId, ResRegistrationParam res) throws Exception;

}

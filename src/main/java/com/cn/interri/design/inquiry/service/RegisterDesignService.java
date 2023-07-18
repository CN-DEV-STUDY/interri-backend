package com.cn.interri.design.inquiry.service;

import com.cn.interri.design.inquiry.dto.RegistReqDto;
import com.cn.interri.design.reply.dto.ResRegistrationParam;

public interface RegisterDesignService {

    void saveDesignRequest(RegistReqDto reqRegistrationParam) throws Exception;
    void saveDesignResponse(long designReqId, ResRegistrationParam res) throws Exception;

}

package com.cn.interri.design.inquiry.service;

import com.cn.interri.design.inquiry.dto.ResRegistrationParam;
import com.cn.interri.design.inquiry.entity.DesignReq;

public interface RegisterService {
    void saveDesignResponse(long designReqId, ResRegistrationParam res) throws Exception;

    void saveDesignInquiry(DesignReq designReq);
}

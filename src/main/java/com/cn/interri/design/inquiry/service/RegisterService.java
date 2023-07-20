package com.cn.interri.design.inquiry.service;

import com.cn.interri.design.inquiry.entity.DesignReq;
import com.cn.interri.design.inquiry.dto.RegistReqDto;
import com.cn.interri.design.reply.dto.ResRegistrationParam;

public interface RegisterService {

    void saveDesignInquiry(DesignReq designReq);
}

package com.cn.interri.design.reply.service;

import com.cn.interri.design.reply.dto.ResRegistrationParam;

public interface RegisterReplyService {
    void saveDesignResponse(long designReqId, ResRegistrationParam res) throws Exception;
}

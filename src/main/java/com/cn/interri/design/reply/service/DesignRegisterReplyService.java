package com.cn.interri.design.reply.service;

import com.cn.interri.design.reply.dto.ReplyRegistParam;

public interface DesignRegisterReplyService {
    void saveDesignResponse(long designReqId, ReplyRegistParam res) throws Exception;
}

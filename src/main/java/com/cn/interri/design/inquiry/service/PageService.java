package com.cn.interri.design.inquiry.service;

import com.cn.interri.design.inquiry.dto.ReqRegistrationResource;
import com.cn.interri.design.inquiry.dto.ReqDetailReqResource;

import java.util.List;

public interface PageService {

    ReqRegistrationResource getRegistrationPageResource();

    ReqDetailReqResource getDesignReqDetails(Long id, String sortType);

    List<String> getResRoomTypeNm(Long id);
}

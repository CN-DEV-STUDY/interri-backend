package com.cn.interri.design.request.service;

import com.cn.interri.design.request.dto.ReqRegistrationResource;
import com.cn.interri.design.request.dto.ReqDetailReqResource;

import java.util.List;

public interface PageService {

    ReqRegistrationResource getRegistrationPageResource();

    ReqDetailReqResource getDesignReqDetails(Long id, String sortType);

    List<String> getResRoomTypeNm(Long id);
}

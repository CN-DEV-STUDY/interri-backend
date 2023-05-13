package com.cn.interri.design.service;

import com.cn.interri.design.dto.ReqRegistrationResource;
import com.cn.interri.design.dto.ReqDetailReqResource;

import java.util.List;

public interface PageService {

    ReqRegistrationResource getRegistrationPageResource();

    ReqDetailReqResource getDesignReqDetails(Long id, String sortType);

    List<String> getResRoomTypeNm(Long id);
}

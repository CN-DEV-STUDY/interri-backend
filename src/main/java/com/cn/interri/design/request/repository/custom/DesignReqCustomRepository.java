package com.cn.interri.design.request.repository.custom;

import com.cn.interri.design.request.dto.ReqDetailReqResource;

public interface DesignReqCustomRepository {
    ReqDetailReqResource getReqDetail(Long id);

//    List<InteriorTrendsDto> getTrends(List<Style> styles);
}

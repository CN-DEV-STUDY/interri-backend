package com.cn.interri.design.inquiry.repository.custom;

import com.cn.interri.common.entity.CommonCode;
import com.cn.interri.design.inquiry.dto.ReqDetailReqResource;
import com.cn.interri.index.dto.InteriorTrendsDto;

import java.util.List;

public interface DesignReqCustomRepository {
    ReqDetailReqResource getReqDetail(Long id);

    List<InteriorTrendsDto> getTrends(List<CommonCode> commonTypes);

//    List<InteriorTrendsDto> getTrends(List<Style> styles);
}
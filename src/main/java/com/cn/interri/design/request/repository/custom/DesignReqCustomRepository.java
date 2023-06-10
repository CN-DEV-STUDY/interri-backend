package com.cn.interri.design.request.repository.custom;

import com.cn.interri.common.entity.Style;
import com.cn.interri.design.request.dto.ReqDetailReqResource;
import com.cn.interri.index.dto.InteriorTrendsResponse;

import java.util.List;

public interface DesignReqCustomRepository {
    ReqDetailReqResource getReqDetail(Long id);

    List<InteriorTrendsResponse> getTrends(List<Style> styles);
}

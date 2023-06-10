package com.cn.interri.design.repository.custom;

import com.cn.interri.design.domain.Style;
import com.cn.interri.design.dto.ReqDetailReqResource;
import com.cn.interri.index.dto.InteriorTrendsResponse;

import java.util.List;

public interface DesignReqCustomRepository {
    ReqDetailReqResource getReqDetail(Long id);

    List<InteriorTrendsResponse> getTrends(List<Style> styles);
}

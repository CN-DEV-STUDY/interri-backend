package com.cn.interri.design.inquiry.repository.custom;

import com.cn.interri.design.inquiry.dto.ReqDetailReqResource;
import com.cn.interri.batch.dto.InteriorTrendDto;

import java.util.List;

public interface DesignReqCustomRepository {
    ReqDetailReqResource getReqDetail(Long id);

    List<InteriorTrendDto> getWeekTrends();
}

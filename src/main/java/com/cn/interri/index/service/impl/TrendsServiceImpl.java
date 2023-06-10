package com.cn.interri.index.service.impl;

import com.cn.interri.common.entity.Style;
import com.cn.interri.design.request.repository.DesignReqRepository;
import com.cn.interri.common.repository.StyleRepository;
import com.cn.interri.index.dto.InteriorTrendsResponse;
import com.cn.interri.index.service.TrendsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class TrendsServiceImpl implements TrendsService {

    private final StyleRepository styleRepository;
    private final DesignReqRepository designReqRepository;


    @Override
    public List<InteriorTrendsResponse> getTrends() {
        List<Style> styles = styleRepository.findAllById(Arrays.asList(1, 9));

        List<InteriorTrendsResponse> responses = designReqRepository.getTrends(styles);
        return responses;
    }
}

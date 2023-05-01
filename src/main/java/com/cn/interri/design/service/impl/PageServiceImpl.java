package com.cn.interri.design.service.impl;

import com.cn.interri.design.enums.Colors;
import com.cn.interri.design.domain.HousingType;
import com.cn.interri.design.domain.RoomType;
import com.cn.interri.design.domain.Size;
import com.cn.interri.design.domain.Style;
import com.cn.interri.design.dto.ReqDetailReqResource;
import com.cn.interri.design.repository.*;
import com.cn.interri.design.service.PageService;
import com.cn.interri.design.dto.ReqRegistrationResource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PageServiceImpl implements PageService {

    private final SizeRepository sizeRepository;
    private final HousingTypeRepository housingTypeRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final StyleRepository styleRepository;
    private final DesignReqRepository designReqRepository;
    private final DesignReqInfoRepository designReqInfoRepository;

    @Override
    public ReqRegistrationResource getRegistrationPageResource() {
        // 평수
        List<Size> sizeList = sizeRepository.findAll();
        // 주거 형태
        List<HousingType> housingTypeList = housingTypeRepository.findAll();
        // 메인, 서브 컬러
        List<String> colorList = Colors.getList();
        // 공간
        List<RoomType> roomTypeList = roomTypeRepository.findAll();
        // 스타일
        List<Style> styleList = styleRepository.findAll();

        return new ReqRegistrationResource(sizeList,
                housingTypeList,
                colorList,
                roomTypeList,
                styleList);
    }

    @Override
    public ReqDetailReqResource getDesignReqDetails() {
        ReqDetailReqResource reqDetail = designReqRepository.getReqDetail();
        return reqDetail;
    }
}

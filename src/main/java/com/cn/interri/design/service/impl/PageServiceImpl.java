package com.cn.interri.design.service.impl;

import com.cn.interri.design.dto.*;
import com.cn.interri.design.enums.Colors;
import com.cn.interri.design.repository.*;
import com.cn.interri.design.service.PageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        List<SizeDto> sizeDtoList = sizeRepository.findAllSize();
        // 주거 형태
        List<HousingTypeDto> housingTypeList = housingTypeRepository.findAllHousingType();
        // 메인, 서브 컬러
        List<String> colorList = Colors.getList();
        // 공간
        List<RoomTypeDto> roomTypeDtoList = roomTypeRepository.findAllRoomType();
        // 스타일
        List<StyleDto> styleList = styleRepository.findAllStyle();

        return new ReqRegistrationResource(sizeDtoList,
                housingTypeList,
                colorList,
                roomTypeDtoList,
                styleList);
    }

    @Override
    public ReqDetailReqResource getDesignReqDetails(Long id) {

        ReqDetailReqResource reqDetail = designReqRepository.getReqDetail(id);
        List<ReqInfoDetailResource> reqInfoDetail = designReqInfoRepository.getReqInfoDetail(id);

        reqDetail.setReqInfoDetailResources(reqInfoDetail);

        return reqDetail;
    }
}

package com.cn.interri.design.service.impl;

import com.cn.interri.design.constant.Colors;
import com.cn.interri.design.domain.HousingType;
import com.cn.interri.design.domain.RoomType;
import com.cn.interri.design.domain.Size;
import com.cn.interri.design.domain.Style;
import com.cn.interri.design.dto.ReqDetailReqResource;
import com.cn.interri.design.repository.HousingTypeRepository;
import com.cn.interri.design.repository.RoomTypeRepository;
import com.cn.interri.design.repository.SizeRepository;
import com.cn.interri.design.repository.StyleRepository;
import com.cn.interri.design.service.PageService;
import com.cn.interri.design.dto.RegistrationResource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class PageServiceImpl implements PageService {

    private final SizeRepository sizeRepository;
    private final HousingTypeRepository housingTypeRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final StyleRepository styleRepository;

    @Override
    public RegistrationResource getRegistrationPageResource() {
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


        return new RegistrationResource(sizeList,
                housingTypeList,
                colorList,
                roomTypeList,
                styleList);
    }

    @Override
    public ReqDetailReqResource getDesignReqDetails() {

        return null;
    }
}

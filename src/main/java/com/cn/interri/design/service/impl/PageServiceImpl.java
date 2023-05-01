package com.cn.interri.design.service.impl;

import com.cn.interri.design.enums.Colors;
import com.cn.interri.design.domain.HousingType;
import com.cn.interri.design.domain.RoomType;
import com.cn.interri.design.domain.Size;
import com.cn.interri.design.domain.Style;
import com.cn.interri.design.repository.HousingTypeRepository;
import com.cn.interri.design.repository.RoomTypeRepository;
import com.cn.interri.design.repository.SizeRepository;
import com.cn.interri.design.repository.StyleRepository;
import com.cn.interri.design.service.PageService;
import com.cn.interri.design.dto.ReqRegistrationResource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PageServiceImpl implements PageService {

    private final SizeRepository sizeRepository;
    private final HousingTypeRepository housingTypeRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final StyleRepository styleRepository;

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
}

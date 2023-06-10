package com.cn.interri.design.request.dto;

import com.cn.interri.common.dto.HousingTypeDto;
import com.cn.interri.common.dto.RoomTypeDto;
import com.cn.interri.common.dto.SizeDto;
import com.cn.interri.common.dto.StyleDto;
import lombok.Value;

import java.util.List;

/**
 * 등록화면에 필요한 리소스
 */
@Value
public class ReqRegistrationResource {

    // 평수
    List<SizeDto> sizeList;

    // 주거 형태
    List<HousingTypeDto> housingTypeList;

    // 메인, 서브 컬러
    List<String> colorList;

    // 공간
    List<RoomTypeDto> roomTypeList;

    // 스타일
    List<StyleDto> styleList;
}

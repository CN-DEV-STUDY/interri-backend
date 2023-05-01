package com.cn.interri.dto;

import com.cn.interri.design.domain.HousingType;
import com.cn.interri.design.domain.RoomType;
import com.cn.interri.design.domain.Size;
import com.cn.interri.design.domain.Style;
import lombok.Value;

import java.util.List;

/**
 * 등록화면에 필요한 리소스
 */
@Value
public class RegistrationResource {

    // 평수
    List<Size> sizeList;

    // 주거 형태
    List<HousingType> housingTypeList;

    // 메인, 서브 컬러
    List<String> colorList;

    // 공간
    List<RoomType> roomTypeList;

    // 스타일
    List<Style> styleList;
}

package com.cn.interri.index.dto;

import com.cn.interri.batch.dto.InteriorTrendsDto;
import lombok.Builder;

import java.util.List;

/**
 * Index 페이지에 필요한 데이터를 반환하는 Dto
 */
@Builder
public record IndexDto (
        HeroDto heroSection,                            // hero 섹션
        List<InteriorTrendsDto> interiorTrendsSection   // 인테리어 트렌드 섹션
) {

}

package com.cn.interri.index.dto;

import lombok.Builder;

import java.util.List;

/**
 * 인테리어 트렌트 스타일 별 리스트
 * @param styleId
 * @param styleName
 * @param styleInfos
 */
@Builder
public record InteriorTrendsDto(
        String styleId,
        String styleName,
        List<StyleInfo> styleInfos
) { }

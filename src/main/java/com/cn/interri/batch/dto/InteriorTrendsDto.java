package com.cn.interri.batch.dto;

import lombok.Builder;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.List;

/**
 * 인테리어 트렌트 스타일 별 리스트
 * @param styleId
 * @param styleName
 * @param styleInfos
 */
@RedisHash("InteriorTrendsDto")
@Builder
public record InteriorTrendsDto(
        String styleId,
        String styleName,
        List<StyleInfoDto> styleInfos
) implements Serializable { }

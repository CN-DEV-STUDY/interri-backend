package com.cn.interri.index.dto;

import lombok.Builder;

/**
 * hero section에 필요한 정보를 반환하는 Dto
 * @param numberOfDesigners
 */
@Builder
public record HeroDto(
        Long numberOfDesigners // 디자이너 수
        // String imgPath          // 이미지 경로
) {
}

package com.cn.interri.index.service.impl;

import com.cn.interri.common.entity.CommonCode;
import com.cn.interri.common.enums.CodeType;
import com.cn.interri.common.repository.CommonTypeRepository;
import com.cn.interri.design.inquiry.repository.DesignReqRepository;
import com.cn.interri.index.dto.HeroDto;
import com.cn.interri.index.dto.IndexDto;
import com.cn.interri.index.dto.InteriorTrendsDto;
import com.cn.interri.index.service.TrendsService;
import com.cn.interri.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class TrendsServiceImpl implements TrendsService {

    private final UserRepository userRepository;
    private final CommonTypeRepository commonTypeRepository;
    private final DesignReqRepository designReqRepository;


    /**
     * 인덱스 페이지에 필요한 데이터를 가져온다.
     */
    @Override
    public IndexDto getIndex() {
        // 1. hero section
        HeroDto heroDto = getHero();

        // 2. 인테리어 트렌드 section
        List<InteriorTrendsDto> interiorTrendsDto = getTrends();

        return IndexDto.builder()
                .heroSection(heroDto)
                .interiorTrendsSection(interiorTrendsDto)
                .build();
    }

    private HeroDto getHero() {
        Long numberOfDesigners = userRepository.countAllDesigners();

        return HeroDto.builder()
                .numberOfDesigners(numberOfDesigners)
                .build();
    }

    private List<InteriorTrendsDto> getTrends() {
        List<CommonCode> commonCodes = commonTypeRepository.findByCodeType(CodeType.STYLE);
        List<InteriorTrendsDto> responses = designReqRepository.getTrends(commonCodes);

        return responses;
    }


}

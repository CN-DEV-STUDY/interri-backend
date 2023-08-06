package com.cn.interri.index.service.impl;

import com.cn.interri.common.dto.RedisKey;
import com.cn.interri.common.repository.CommonTypeRepository;
import com.cn.interri.design.inquiry.repository.DesignReqRepository;
import com.cn.interri.index.dto.HeroDto;
import com.cn.interri.index.dto.IndexDto;
import com.cn.interri.batch.dto.InteriorTrendDto;
import com.cn.interri.index.service.TrendsService;
import com.cn.interri.user.repository.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class TrendsServiceImpl implements TrendsService {

    private final UserRepository userRepository;
    private final RedisTemplate<String, String> redisTemplate;
    private final CommonTypeRepository commonTypeRepository;
    private final DesignReqRepository designReqRepository;


    /**
     * 인덱스 페이지에 필요한 데이터를 가져온다.
     */
    @Override
    public IndexDto getIndex() throws IOException {
        // 1. hero section
        HeroDto heroDto = getHero();

        // 2. 인테리어 트렌드 section
        List<InteriorTrendDto> interiorTrendsDto = getTrends();

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

    private List<InteriorTrendDto> getTrends() throws IOException {
        ListOperations<String, String> listOperations = redisTemplate.opsForList();
        long size = listOperations.size(RedisKey.INTERIOR_TREND) == null ? 0 : listOperations.size(RedisKey.INTERIOR_TREND);
        List<String> redisValues = listOperations.range(RedisKey.INTERIOR_TREND, 0, size);

        List<InteriorTrendDto> interiorTrendsDtos = new ArrayList<>();
        for (String redisValue : redisValues) {
            log.info("reading data from redis");
            InteriorTrendDto interiorTrendsDto = new ObjectMapper().readValue(redisValue, new TypeReference<>() {});
            interiorTrendsDtos.add(interiorTrendsDto);
        }

        return interiorTrendsDtos;
    }


}

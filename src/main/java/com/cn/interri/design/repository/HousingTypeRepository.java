package com.cn.interri.design.repository;

import com.cn.interri.design.domain.HousingType;
import com.cn.interri.design.dto.HousingTypeDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HousingTypeRepository extends JpaRepository<HousingType, Integer> {

    @Query("SELECT new com.cn.interri.design.dto.HousingTypeDto(ht.id, ht.housingTypeNm) FROM HousingType ht")
    List<HousingTypeDto> findAllHousingType();
}

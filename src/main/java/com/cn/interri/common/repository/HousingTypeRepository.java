package com.cn.interri.common.repository;

import com.cn.interri.common.dto.HousingTypeDto;
import com.cn.interri.common.entity.HousingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HousingTypeRepository extends JpaRepository<HousingType, Integer> {

    @Query("SELECT new com.cn.interri.common.dto.HousingTypeDto(ht.id, ht.housingTypeNm) FROM HousingType ht")
    List<HousingTypeDto> findAllHousingType();
}

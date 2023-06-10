package com.cn.interri.common.repository;

import com.cn.interri.common.dto.StyleDto;
import com.cn.interri.common.entity.Style;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StyleRepository extends JpaRepository<Style, Integer> {


    @Query("SELECT new com.cn.interri.common.dto.StyleDto(s.id, s.styleNm) FROM Style s")
    List<StyleDto> findAllStyle();
}
package com.cn.interri.design.repository;

import com.cn.interri.design.domain.Style;
import com.cn.interri.design.dto.StyleDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StyleRepository extends JpaRepository<Style, Long> {


    @Query("SELECT new com.cn.interri.design.dto.StyleDto(s.id, s.styleNm) FROM Style s")
    List<StyleDto> findAllStyle();
}

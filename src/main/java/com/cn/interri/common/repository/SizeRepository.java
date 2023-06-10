package com.cn.interri.common.repository;

import com.cn.interri.common.dto.SizeDto;
import com.cn.interri.common.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SizeRepository extends JpaRepository<Size, Integer> {

    @Query("SELECT new com.cn.interri.common.dto.SizeDto(z.id, z.sizeNm) FROM Size z")
    List<SizeDto> findAllSize();

}
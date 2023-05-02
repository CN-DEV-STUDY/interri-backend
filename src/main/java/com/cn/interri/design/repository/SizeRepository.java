package com.cn.interri.design.repository;

import com.cn.interri.design.domain.Size;
import com.cn.interri.design.dto.SizeDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SizeRepository extends JpaRepository<Size, Long> {

    @Query("SELECT new com.cn.interri.design.dto.SizeDto(z.id, z.sizeNm) FROM Size z")
    List<SizeDto> findAllSize();

}

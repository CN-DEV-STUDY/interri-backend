package com.cn.interri.design.repository;

import com.cn.interri.design.domain.DesignResInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DesignResInfoRepository extends JpaRepository<DesignResInfo, Long> {

    DesignResInfo findTopByDesignRes_IdAndDelYn(Long id, String delYn);
}

package com.cn.interri.design.request.repository;

import com.cn.interri.design.request.entity.DesignResInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesignResInfoRepository extends JpaRepository<DesignResInfo, Long> {

    DesignResInfo findTopByDesignRes_IdAndDelYn(Long id, String delYn);
}

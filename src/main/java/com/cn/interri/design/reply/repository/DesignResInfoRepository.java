package com.cn.interri.design.reply.repository;

import com.cn.interri.design.inquiry.entity.DesignResInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesignResInfoRepository extends JpaRepository<DesignResInfo, Long> {

    DesignResInfo findTopByDesignRes_IdAndDelYn(Long id, String delYn);
}

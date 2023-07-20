package com.cn.interri.design.reply.repository;

import com.cn.interri.design.inquiry.entity.DesignRes;
import com.cn.interri.design.reply.repository.custom.DesignResCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesignResRepository extends JpaRepository<DesignRes, Long> , DesignResCustomRepository {
}

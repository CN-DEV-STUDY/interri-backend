package com.cn.interri.design.inquiry.repository.custom;

import com.cn.interri.design.inquiry.entity.DesignRes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesignResRepository extends JpaRepository<DesignRes, Long> , DesignResCustomRepository {
}

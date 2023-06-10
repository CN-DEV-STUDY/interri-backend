package com.cn.interri.design.request.repository.custom;

import com.cn.interri.design.request.entity.DesignRes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesignResRepository extends JpaRepository<DesignRes, Long> , DesignResCustomRepository {
}

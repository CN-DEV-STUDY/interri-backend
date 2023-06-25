package com.cn.interri.common.repository;

import com.cn.interri.common.entity.CommonType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommonTypeRepository extends JpaRepository<CommonType, Integer> {
    CommonType findByType(String type);
}

package com.cn.interri.common.repository;

import com.cn.interri.common.entity.CommonTypeDesign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommonTypeDesignRepository extends JpaRepository<CommonTypeDesign, Integer> {
    CommonTypeDesign findByDesignReqInfo_Id(Long id);
}

package com.cn.interri.common.repository;

import com.cn.interri.common.entity.CommonCodeDesign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommonTypeDesignRepository extends JpaRepository<CommonCodeDesign, Integer> {
    CommonCodeDesign findByDesignReqInfo_Id(Long id);
}

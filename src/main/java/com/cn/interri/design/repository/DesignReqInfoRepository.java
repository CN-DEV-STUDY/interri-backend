package com.cn.interri.design.repository;

import com.cn.interri.design.domain.DesignReqInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DesignReqInfoRepository extends JpaRepository<DesignReqInfo , Long> {
    List<DesignReqInfo> findAllByDesignReq_Id(Long id);
}

package com.cn.interri.design.inquiry.repository;

import com.cn.interri.design.inquiry.entity.DesignReqInfo;
import com.cn.interri.design.inquiry.repository.custom.DesignReqInfoCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesignReqInfoRepository extends JpaRepository<DesignReqInfo, Long>, DesignReqInfoCustomRepository {
}

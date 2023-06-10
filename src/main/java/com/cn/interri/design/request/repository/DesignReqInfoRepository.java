package com.cn.interri.design.request.repository;

import com.cn.interri.design.request.entity.DesignReqInfo;
import com.cn.interri.design.request.repository.custom.DesignReqInfoCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesignReqInfoRepository extends JpaRepository<DesignReqInfo, Long>, DesignReqInfoCustomRepository {
}

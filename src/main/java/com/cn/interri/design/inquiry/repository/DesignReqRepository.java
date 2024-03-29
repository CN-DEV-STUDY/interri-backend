package com.cn.interri.design.inquiry.repository;

import com.cn.interri.design.inquiry.entity.DesignReq;
import com.cn.interri.design.inquiry.repository.custom.DesignReqCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesignReqRepository extends JpaRepository<DesignReq, Long> , DesignReqCustomRepository {
}

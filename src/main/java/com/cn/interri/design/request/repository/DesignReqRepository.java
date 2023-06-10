package com.cn.interri.design.request.repository;

import com.cn.interri.design.request.entity.DesignReq;
import com.cn.interri.design.request.repository.custom.DesignReqCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesignReqRepository extends JpaRepository<DesignReq, Long> , DesignReqCustomRepository {
}

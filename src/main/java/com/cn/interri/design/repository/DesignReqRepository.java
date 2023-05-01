package com.cn.interri.design.repository;

import com.cn.interri.design.domain.DesignReq;
import com.cn.interri.design.repository.custom.DesignReqCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesignReqRepository extends JpaRepository<DesignReq, Long> , DesignReqCustomRepository {
}

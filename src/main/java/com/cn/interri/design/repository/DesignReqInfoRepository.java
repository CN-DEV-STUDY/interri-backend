package com.cn.interri.design.repository;

import com.cn.interri.design.domain.DesignReqInfo;
import com.cn.interri.design.repository.custom.DesignReqInfoCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DesignReqInfoRepository extends JpaRepository<DesignReqInfo , Long>, DesignReqInfoCustomRepository {
}

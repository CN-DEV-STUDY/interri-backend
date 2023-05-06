package com.cn.interri.design.repository;

import com.cn.interri.design.domain.FileDesignReq;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileDesignReqRepository extends JpaRepository<FileDesignReq, Long> {
    List<FileDesignReq> findByDesignReqInfo_Id(Long id);
}

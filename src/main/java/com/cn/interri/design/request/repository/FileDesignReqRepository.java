package com.cn.interri.design.request.repository;

import com.cn.interri.design.request.entity.FileDesignReq;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileDesignReqRepository extends JpaRepository<FileDesignReq, Long> {
    List<FileDesignReq> findByDesignReqInfo_Id(Long id);
}

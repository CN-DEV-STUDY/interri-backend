package com.cn.interri.design.inquiry.repository;

import com.cn.interri.design.inquiry.entity.FileDesignReq;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileDesignReqRepository extends JpaRepository<FileDesignReq, Long> {
    FileDesignReq findByDesignReqInfo_IdAndDelYn(Long id, String delYn);
}

package com.cn.interri.design.inquiry.repository;

import com.cn.interri.design.inquiry.entity.FileDesignRes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileDesignResRepository extends JpaRepository<FileDesignRes, Long> {
    FileDesignRes findTopByDesignResInfo_IdAndDelYn(Long id,String delYn);
}

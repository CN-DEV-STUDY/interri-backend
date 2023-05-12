package com.cn.interri.design.repository;

import com.cn.interri.design.domain.FileDesignRes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FileDesignResRepository extends JpaRepository<FileDesignRes, Long> {
    FileDesignRes findTopByDesignResInfo_IdAndDelYn(Long id,String delYn);
}

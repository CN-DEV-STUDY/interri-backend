package com.cn.interri.design.reply.repository;

import com.cn.interri.design.reply.entity.FileDesignReply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileDesignResRepository extends JpaRepository<FileDesignReply, Long> {
    FileDesignReply findTopByDesignResInfo_IdAndDelYn(Long id, String delYn);
}

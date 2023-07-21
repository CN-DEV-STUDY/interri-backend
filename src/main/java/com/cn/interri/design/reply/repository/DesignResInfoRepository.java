package com.cn.interri.design.reply.repository;

import com.cn.interri.design.reply.entity.DesignReplyInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesignResInfoRepository extends JpaRepository<DesignReplyInfo, Long> {

    DesignReplyInfo findTopByDesignRes_IdAndDelYn(Long id, String delYn);
}

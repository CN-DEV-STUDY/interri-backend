package com.cn.interri.design.reply.repository;

import com.cn.interri.design.reply.entity.DesignReplyInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesignReplyInfoRepository extends JpaRepository<DesignReplyInfo, Long> {

    DesignReplyInfo findTopByDesignReply_IdAndDelYn(Long id, String delYn);
}

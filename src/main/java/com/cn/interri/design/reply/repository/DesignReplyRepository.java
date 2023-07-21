package com.cn.interri.design.reply.repository;

import com.cn.interri.design.reply.entity.DesignReply;
import com.cn.interri.design.reply.repository.custom.DesignReplyCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesignReplyRepository extends JpaRepository<DesignReply, Long> , DesignReplyCustomRepository {
}

package com.cn.interri.batch.entity;

import com.cn.interri.common.entity.ImmutableBaseTimeEntity;
import com.cn.interri.design.reply.entity.DesignReply;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class AdoptionHistory extends ImmutableBaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adoption_history_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "design_reply_id")
    DesignReply designReply;
}

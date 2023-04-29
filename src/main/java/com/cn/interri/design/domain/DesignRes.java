package com.cn.interri.design.domain;

import com.cn.interri.user.domain.User.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "design_res")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class DesignRes {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "design_res_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}

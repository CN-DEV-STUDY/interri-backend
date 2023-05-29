package com.cn.interri.user.domain.User;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
public class Authorities {

    @Id @Column(name = "user_id")
    private Long id;

    private String authority;
}

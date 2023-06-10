package com.cn.interri.user.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;


@Entity
@Getter
public class Authorities {

    @Id
    @Column(name = "user_id")
    private Long id;

    private String authority;
}

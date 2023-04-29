package com.cn.interri.user.domain.User;

import com.cn.interri.common.BaseTimeEntity;
import com.cn.interri.user.constant.SignType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id; // 사용자 id

    @Column(length = 20, nullable = false)
    private String email; // 사용자 이메일

    @Column(length = 20, nullable = false)
    private String password; // 사용자 비밀번호

    @Enumerated(EnumType.STRING)
    @Column(length = 1, nullable = false)
    private SignType signType; // 가입 타입

    @Column(length = 50)
    private String address; // 사용자 주소

    @Column(length = 20)
    private String phone; // 사용자 전화번호
}

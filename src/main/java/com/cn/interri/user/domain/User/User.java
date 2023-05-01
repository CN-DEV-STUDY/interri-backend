package com.cn.interri.user.domain.User;

import com.cn.interri.common.BaseTimeEntity;
import com.cn.interri.design.domain.DesignReq;
import com.cn.interri.user.constant.SignType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @Comment("사용자 id")
    private Long id;

    @Column(length = 20, nullable = false)
    @Comment("사용자 닉네임")
    private String nickname;

    @Column(length = 20, nullable = false)
    @Comment("사용자 이메일")
    private String email;

    @Column(length = 20, nullable = false)
    @Comment("사용자 비밀번호")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(length = 1, nullable = false)
    @Comment("가입 타입")
    private SignType signType;

    @Column(length = 50)
    @Comment("사용자 주소")
    private String address;

    @Column(length = 20)
    @Comment("사용자 전화번호")
    private String phone;

    @ColumnDefault("0")
    @Comment("채택수")
    private int adoptionCnt;

    @Column(length = 100)
    @Comment("프로필 이미지 파일 이름")
    private String profileImgNm;

    @Column(length = 100)
    @Comment("프로필 이미지 저장 경로")
    private String profileImgPath;

    @OneToMany(mappedBy = "user")
    private List<DesignReq> designReqArrayList = new ArrayList<>();
}

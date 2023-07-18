package com.cn.interri.user.entity.User;

import com.cn.interri.common.entity.BaseTimeEntity;
import com.cn.interri.design.inquiry.entity.DesignReq;
import com.cn.interri.user.enums.SignType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User extends BaseTimeEntity implements UserDetails {

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
    @Column(nullable = false)
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

    @Column(length = 1)
    @Comment("사용여부")
    private String enableYn;

    @OneToMany(mappedBy = "user")
    private List<DesignReq> designReqArrayList = new ArrayList<>();

    @OneToMany(mappedBy = "id", fetch = FetchType.EAGER)
    private List<Authorities> authorities = new ArrayList<>();

    // ### UserDetails 메서드 구현
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities.stream()
                .map(authority -> authority.getAuthority())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

package com.cn.interri.user.domain.User;

import com.cn.interri.user.enums.DesignType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "scrab")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Scrab {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scrab_id")
    @Comment("스크랩 id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @Comment("사용자")
    private User user;

    @Comment("디자인 id")
    private Long designId;

    @Enumerated(EnumType.STRING)
    @Comment("디자인 타입")
    private DesignType designType;
}

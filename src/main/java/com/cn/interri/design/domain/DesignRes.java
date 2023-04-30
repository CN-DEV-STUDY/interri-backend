package com.cn.interri.design.domain;

import com.cn.interri.common.BaseTimeEntity;
import com.cn.interri.user.domain.User.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Table(name = "design_res")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class DesignRes extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "design_res_id")
    @Comment("디자인 응답 id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @Comment("사용자")
    private User user; // 사용자

    @Column(nullable = false)
    @Comment("응답 가격")
    private Integer price; // 응답 가격

    @Column(length = 500, nullable = false)
    @Comment("응답 내용")
    private String content;

    @Column(length = 1, nullable = false)
    @Comment("삭제 여부")
    @ColumnDefault("N")
    private String delYn;

    @Comment("조회수")
    private Integer viewCnt;

    @Comment("스크랩 수")
    private Integer scrabCnt;
}

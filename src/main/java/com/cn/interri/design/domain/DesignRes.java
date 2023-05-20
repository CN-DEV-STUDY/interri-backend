package com.cn.interri.design.domain;

import com.cn.interri.common.entity.BaseTimeEntity;
import com.cn.interri.user.domain.User.User;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "design_res")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class DesignRes extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "design_res_id")
    @Comment("디자인 응답 id")
    private Long id;

    @Column(nullable = false)
    @Comment("응답 가격")
    private Integer price; // 응답 가격

    @Column(length = 1, nullable = false)
    @Comment("삭제 여부")
    @ColumnDefault("N")
    private String delYn;

    @Comment("조회수")
    private Integer viewCnt;

    @Comment("스크랩 수")
    private Integer scrabCnt;

    @Comment("디자인 요청 id")
    private Long designReqId;

    @OneToMany(mappedBy = "designRes" , cascade = CascadeType.ALL)
    @Comment("디자인 응답 정보 리스트")
    private List<DesignResInfo> designResInfoList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @Comment("디자인 응답 글쓴이")
    private User user; // 사용자
}

package com.cn.interri.design.domain;

import com.cn.interri.common.BaseTimeEntity;
import com.cn.interri.user.domain.User.User;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "design_req")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class DesignReq extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "design_req_id" ,nullable = false)
    private Long id;

    @Column(length = 7)
    @Comment("메인 컬러")
    private String mainColor;

    @Column(length = 7)
    @Comment("서브 컬러")
    private String subColor;

    @Comment("최대 가격")
    private Integer maxPrice;

    @Comment("마감 기한")
    private LocalDate dueDate;

    @Column(length = 1)
    @Comment("임시 저장 여부")
    @ColumnDefault("N")
    private String tempYn;

    @Column(length = 1)
    @Comment("삭제 여부")
    @ColumnDefault("N")
    private String delYn;

    @Comment("조회수")
    @ColumnDefault("0")
    private int viewCnt;

    @Comment("스크랩수")
    @ColumnDefault("0")
    private int scrabCnt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "designReq")
    private List<DesignReqInfo> designReqInfoList = new ArrayList<>();

    @OneToMany(mappedBy = "designReq")
    private List<DesignReqHousingType> designReqHousingTypeList = new ArrayList<>();

    @OneToMany(mappedBy = "designReq")
    private List<DesignReqStyle> designReqStyleList = new ArrayList<>();
}

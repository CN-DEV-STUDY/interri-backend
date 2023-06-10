package com.cn.interri.design.request.entity;

import com.cn.interri.common.entity.BaseTimeEntity;
import com.cn.interri.common.entity.HousingType;
import com.cn.interri.common.entity.Size;
import com.cn.interri.common.entity.Style;
import com.cn.interri.user.entity.User.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "design_req")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    @Comment("디자인 요청 글쓴이")
    private User user;

    @OneToMany(mappedBy = "designReq", cascade = CascadeType.ALL)
    @Comment("디자인 요청 정보 리스트")
    private List<DesignReqInfo> designReqInfoList = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "housing_type_id")
    @Comment("공간")
    private HousingType housingType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "style_id")
    @Comment("스타일")
    private Style style;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "size_id")
    @Comment("평답")
    private Size size;

    public DesignReq(String mainColor, String subColor, Integer maxPrice, LocalDate dueDate, String tempYn, String delYn, User user, List<DesignReqInfo> designReqInfoList, HousingType housingType, Style style, Size size) {
        this.mainColor = mainColor;
        this.subColor = subColor;
        this.maxPrice = maxPrice;
        this.dueDate = dueDate;
        this.tempYn = tempYn;
        this.delYn = delYn;
        this.user = user;
        for (DesignReqInfo designReqInfo : designReqInfoList) {
            this.designReqInfoList.add(designReqInfo);
            designReqInfo.setDesignReq(this);
        }
        this.housingType = housingType;
        this.style = style;
        this.size = size;
    }

}

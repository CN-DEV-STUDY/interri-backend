package com.cn.interri.design.inquiry.entity;

import com.cn.interri.common.entity.BaseTimeEntity;
import com.cn.interri.common.entity.CommonCodeDesign;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "design_req_info")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DesignReqInfo extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "design_req_info_id")
    private Long id;

    @Column(length = 500)
    @Comment("요청 내용")
    private String content;

    @Column(length = 1)
    @Comment("삭제 여부")
    @ColumnDefault("N")
    private String delYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "design_req_id")
    private DesignReq designReq;

    @OneToOne(mappedBy = "designReqInfo", cascade = CascadeType.ALL)
    private FileDesignReq fileDesignReq;

    @OneToMany(mappedBy = "designReqInfo", cascade = CascadeType.ALL)
    private List<CommonCodeDesign> commonCodeDesigns = new ArrayList<>();

    public void setDesignReq(DesignReq designReq) {
        this.designReq = designReq;
    }

    @Builder
    public DesignReqInfo(String content, FileDesignReq fileDesignReq) {
        this.content = content;
        this.delYn = "N";

        // 연관관계 매핑
        this.fileDesignReq = fileDesignReq;
        fileDesignReq.setDesignReqInfo(this);
    }
}

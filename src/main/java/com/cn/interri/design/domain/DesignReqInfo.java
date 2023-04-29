package com.cn.interri.design.domain;

import com.cn.interri.common.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "design_req_info")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class DesignReqInfo extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "design_reg_info_id")
    private Long id;

    @Column(length = 1)
    @Comment("삭제 여부")
    @ColumnDefault("N")
    private String delYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "design_req_id")
    private DesignReq designReq;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_design_req_id")
    private FileDesignReq fileDesignReq;

    @OneToMany(mappedBy = "designReqInfo")
    private List<DesignReqRoomType> designReqRoomTypeList = new ArrayList<>();

    @OneToMany(mappedBy = "designReqInfo")
    private List<DesignReqSize> designReqSizeList = new ArrayList<>();
}
package com.cn.interri.common.entity;

import com.cn.interri.design.request.entity.DesignReq;
import com.cn.interri.design.request.entity.DesignReqInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class CommonTypeDesign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "common_type_design_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "common_type_id")
    private CommonType commonType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "design_req_id")
    private DesignReq designReq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "design_req_info_id")
    private DesignReqInfo designReqInfo;
}

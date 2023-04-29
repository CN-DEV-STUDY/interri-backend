package com.cn.interri.design.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "design_req_size")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class DesignReqSize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "design_reg_size_id")
    private Long id;

    //TODO 평수
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "size_id")
    private Size size;

    //TODO 디자인 요청
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "design_reg_info_id")
    private DesignReqInfo designReqInfo;
}

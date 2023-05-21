package com.cn.interri.design.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "housing_type")
@Getter
@NoArgsConstructor
public class HousingType {
    @Id
    @Column(name = "housing_type_id")
    @Comment("주거 형태 id")
    private Integer id;

    @Column(length = 10)
    @Comment("주거 형태 타입")
    private String housingTypeNm;

    @OneToOne(mappedBy = "housingType", fetch = FetchType.LAZY)
    private DesignReq designReq;
}

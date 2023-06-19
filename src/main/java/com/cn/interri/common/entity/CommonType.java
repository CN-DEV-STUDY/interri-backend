package com.cn.interri.common.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class CommonType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("공통 타입 id")
    @Column(name = "common_type_id")
    private Integer id;
    @Comment("공통 타입 이름")
    @Column(name = "common_type_nm")
    private String name;

    @Comment("구별 타입")
    @Column(name = "DTYPE")
    private String type;

    @OneToMany(mappedBy = "commonType")
    private List<CommonTypeDesign> commonTypeDesigns = new ArrayList<>();

}

package com.cn.interri.design.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Table(name = "size")
@Getter
@NoArgsConstructor
public class Size {
    @Id
    @Column(name = "size_id")
    @Comment("평수 id")
    private Integer id;

    @Column(length = 10)
    @Comment("평수명")
    private String sizeNm;

    @OneToOne(mappedBy = "size", fetch = FetchType.LAZY)
    private DesignReq designReq;
}

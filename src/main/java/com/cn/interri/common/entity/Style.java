package com.cn.interri.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;


@Entity
@Table(name = "style")
@Getter
@NoArgsConstructor
public class Style {
    @Id
    @Column(name = "style_id")
    @Comment("스타일 id")
    private Integer id;

    @Column(length = 10)
    @Comment("스타일명")
    private String styleNm;

//    @OneToOne(mappedBy = "style", fetch = FetchType.LAZY)
//    private DesignReq designReq;
}

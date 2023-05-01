package com.cn.interri.design.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "style")
@Getter
@NoArgsConstructor
public class Style {
    @Id
    @Column(name = "style_id")
    @Comment("스타일 id")
    private int id;

    @Column(length = 10)
    @Comment("스타일명")
    private String styleNm;

    @OneToOne(mappedBy = "style")
    private DesignReq designReq;
}

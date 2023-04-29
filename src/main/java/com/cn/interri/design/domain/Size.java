package com.cn.interri.design.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "size")
@Getter
@NoArgsConstructor
public class Size {
    @Id
    @Column(name = "size_id")
    @Comment("평수 id")
    private int id;

    @Column(length = 10)
    @Comment("평수명")
    private String sizeNm;

    @OneToMany(mappedBy = "size")
    private List<DesignReqSize> designReqSizeList = new ArrayList<>();
}

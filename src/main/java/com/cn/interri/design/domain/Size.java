package com.cn.interri.design.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

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

//    @OneToMany(mappedBy = "size", fetch = FetchType.LAZY)
//    private List<DesignReq> designReqList = new ArrayList<>();
}

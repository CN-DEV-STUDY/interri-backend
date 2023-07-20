package com.cn.interri.design.inquiry.entity;

import com.cn.interri.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "design_res_info")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class DesignResInfo extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "design_res_info_id")
    private Long id;

    @Column(length = 500)
    @Comment("응답 내용")
    private String content;

    @Column(length = 1)
    @Comment("삭제 여부")
    @ColumnDefault("N")
    private String delYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "design_res_id")
    private DesignRes designRes;

    @OneToMany(mappedBy = "designResInfo" , cascade = CascadeType.ALL)
    private List<FileDesignRes> fileDesignResList = new ArrayList<>();

    public void setDesignRes(DesignRes designRes) {
        this.designRes = designRes;
    }

    //=== 양방향 메서드 ===//
    public void addFileDesignRes(FileDesignRes file){
        fileDesignResList.add(file);
        file.setDesignResInfo(this);
    }

    //=== 생성 메서드  ===//
    public static DesignResInfo createDesignResInfo(String content , String delYn, FileDesignRes fileDesignResList){
        DesignResInfo info = new DesignResInfo();

        info.content = content;
        info.delYn = delYn;

        info.addFileDesignRes(fileDesignResList);


        return info;
    }
}

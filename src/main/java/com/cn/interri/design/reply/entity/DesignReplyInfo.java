package com.cn.interri.design.reply.entity;

import com.cn.interri.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "design_reply_info")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class DesignReplyInfo extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "design_reply_info_id")
    private Long id;

    @Column(length = 500)
    @Comment("응답 내용")
    private String content;

    @Column(length = 1)
    @Comment("삭제 여부")
    @ColumnDefault("N")
    private String delYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "design_reply_id")
    private DesignReply designRes;

    @OneToMany(mappedBy = "designResInfo" , cascade = CascadeType.ALL)
    private List<FileDesignReply> fileDesignResList = new ArrayList<>();

    public void setDesignRes(DesignReply designRes) {
        this.designRes = designRes;
    }

    //=== 양방향 메서드 ===//
    public void addFileDesignRes(FileDesignReply file){
        fileDesignResList.add(file);
        file.setDesignResInfo(this);
    }

    //=== 생성 메서드  ===//
    public static DesignReplyInfo createDesignReplyInfo(String content , String delYn, FileDesignReply file){
        DesignReplyInfo info = new DesignReplyInfo();

        info.content = content;
        info.delYn = delYn;


        info.addFileDesignRes(file);


        return info;
    }
}

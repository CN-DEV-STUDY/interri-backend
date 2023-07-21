package com.cn.interri.design.reply.entity;

import com.cn.interri.common.entity.BaseTimeEntity;
import com.cn.interri.user.entity.User.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "design_reply")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class DesignReply extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "design_reply_id")
    @Comment("디자인 응답 id")
    private Long id;

    @Column(nullable = false)
    @Comment("응답 가격")
    private Integer price; // 응답 가격

    @Column(length = 1, nullable = false)
    @Comment("삭제 여부")
    @ColumnDefault("N")
    private String delYn;

    @Comment("조회수")
    private Integer viewCnt;

    @Comment("스크랩 수")
    private Integer scrabCnt;

    @Comment("디자인 요청 id")
    private Long designReqId;

    @OneToMany(mappedBy = "designReply" , cascade = CascadeType.ALL)
    @Comment("디자인 응답 정보 리스트")
    private List<DesignReplyInfo> designResInfoList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @Comment("디자인 응답 글쓴이")
    private User user; // 사용자

    //=== 양방향 메서드 ===//
    public void addDesignResInfo(DesignReplyInfo info){
        designResInfoList.add(info);
        info.setDesignRes(this);
    }

    //=== 생성 메서드  ===//
    public static DesignReply createDesignReply(Long designReqId, User user, int price, String delYn, List<DesignReplyInfo> designResInfoList){
        DesignReply res = new DesignReply();
        res.designReqId = designReqId;
        res.user = user;
        res.price = price;
        res.delYn = delYn;

        // 양방향으로 데이터 넣어줌
        for (DesignReplyInfo info : designResInfoList) {
            res.addDesignResInfo(info);
        }

        return res;
    }
}

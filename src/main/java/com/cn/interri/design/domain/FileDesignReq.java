package com.cn.interri.design.domain;

import com.cn.interri.common.entity.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Table(name = "file_design_req")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FileDesignReq extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_design_req_id")
    private Long id;

    @Column(length = 100, nullable = false)
    @Comment("파일 저장 경로")
    private String filePath;

    @Column(length = 100, nullable = false)
    @Comment("파일 이름")
    private String fileNm;


    @Column(length = 1)
    @Comment("삭제 여부")
    @ColumnDefault("N")
    private String delYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "design_req_info_id")
    private DesignReqInfo designReqInfo;
}

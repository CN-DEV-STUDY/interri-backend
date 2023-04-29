package com.cn.interri.design.domain;

import com.cn.interri.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Table(name = "file_design_res")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class FileDesignRes extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_design_id")
    @Comment("파일 id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "design_res_id")
    @Comment("디자인 응답")
    private DesignRes designRes;

    @Column(length = 100, nullable = false)
    @Comment("파일 저장 경로")
    private String filePath;

    @Column(length = 100, nullable = false)
    @Comment("파일 이름")
    private String fileNm;

    @Column(length = 5, nullable = false)
    @Comment("파일 확장자")
    private String fileExt;

    @Column(length = 1, nullable = false)
    @Comment("삭제 여부")
    private String delYn;
}

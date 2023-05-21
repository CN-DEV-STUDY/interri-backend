package com.cn.interri.design.domain;

import com.cn.interri.common.entity.BaseTimeEntity;
import com.cn.interri.common.utils.FileUtils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "file_design_res")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class FileDesignRes extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_design_res_id")
    @Comment("파일 id")
    private Long id;

    @Column(length = 100, nullable = false)
    @Comment("파일 저장 경로")
    private String filePath;

    @Column(length = 100, nullable = false)
    @Comment("파일 이름")
    private String fileNm;

    @Column(length = 1, nullable = false)
    @Comment("삭제 여부")
    private String delYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "design_res_info_id")
    @Comment("디자인 응답 정보 id")
    private DesignResInfo designResInfo;

    public FileDesignRes(String filePath, String fileNm) {
        this.filePath = filePath;
        this.fileNm = fileNm;
    }

    public void setDesignResInfo(DesignResInfo designResInfo) {
        this.designResInfo = designResInfo;
    }


    public static List<FileDesignRes> createFileDesignRes(List<MultipartFile> multipartFiles, String resPath){
        return multipartFiles.stream()
                .map(multipartFile -> {
                    String filePath = resPath + FileUtils.getUuidFileName(multipartFile.getOriginalFilename());
                    return new FileDesignRes(filePath, multipartFile.getOriginalFilename());
                }).collect(Collectors.toList());
    }
}

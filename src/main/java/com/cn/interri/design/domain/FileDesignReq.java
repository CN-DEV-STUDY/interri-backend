package com.cn.interri.design.domain;

import com.cn.interri.common.entity.BaseTimeEntity;
import com.cn.interri.common.utils.FileUtils;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

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
    @Comment("디자인 요청 정보 id")
    private DesignReqInfo designReqInfo;

    public FileDesignReq(String filePath, String fileNm) {
        this.filePath = filePath;
        this.fileNm = fileNm;
    }

    public static List<FileDesignReq> createFileDesignReq(List<MultipartFile> multipartFiles) {
        return multipartFiles.stream()
                    .map(multipartFile -> {
                        String filePath = FileUtils.REQUEST_PATH + FileUtils.getUuidFileName(multipartFile.getOriginalFilename());
                        return new FileDesignReq(filePath, multipartFile.getOriginalFilename());
                    })
                    .collect(Collectors.toList());
    }
}

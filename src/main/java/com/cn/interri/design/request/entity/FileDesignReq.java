package com.cn.interri.design.request.entity;

import com.cn.interri.common.entity.BaseTimeEntity;
import com.cn.interri.common.utils.FileUtils;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.springframework.web.multipart.MultipartFile;

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

    public void setDesignReqInfo(DesignReqInfo designReqInfo) {
        this.designReqInfo = designReqInfo;
    }

    public FileDesignReq(String filePath, String fileNm, String delYn) {
        this.filePath = filePath;
        this.fileNm = fileNm;
        this.delYn = delYn;
    }

    public static FileDesignReq createFileDesignReq(MultipartFile multipartFile) {
        String fileExt = FileUtils.getExt(multipartFile.getOriginalFilename());
        String filePath = FileUtils.REQUEST_PATH + FileUtils.getUuidFileName(fileExt);

        return new FileDesignReq(filePath, multipartFile.getOriginalFilename(), "N");
    }
}

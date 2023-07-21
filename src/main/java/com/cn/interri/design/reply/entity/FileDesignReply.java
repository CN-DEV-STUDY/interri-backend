package com.cn.interri.design.reply.entity;

import com.cn.interri.common.entity.BaseTimeEntity;
import com.cn.interri.common.utils.FileUtils;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "file_design_reply")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class FileDesignReply extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_design_reply_id")
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "design_reply_info_id")
    @Comment("디자인 응답 정보 id")
    private DesignReplyInfo designReplyInfo;

    public FileDesignReply(String filePath, String fileNm , String delYn) {
        this.filePath = filePath;
        this.fileNm = fileNm;
        this.delYn = delYn;
    }

    public void setDesignResInfo(DesignReplyInfo designResInfo) {
        this.designReplyInfo = designResInfo;
    }


    public static FileDesignReply createFileDesignReply(MultipartFile multipartFile, String resPath){
        String filePath = resPath + FileUtils.getUuidFileName(multipartFile.getOriginalFilename());

        return new FileDesignReply(filePath, multipartFile.getOriginalFilename(),"N");
    }
}

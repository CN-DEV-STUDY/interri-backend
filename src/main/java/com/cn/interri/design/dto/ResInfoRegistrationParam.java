package com.cn.interri.design.dto;
import lombok.Getter;
import org.hibernate.annotations.Comment;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
public class ResInfoRegistrationParam {

    @Comment("사진")
    private MultipartFile[] imgFiles;

    @Comment("공간 id")
    private int roomTypeId;

    @Comment("사진 설명")
    private String content;

}

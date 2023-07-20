package com.cn.interri.design.inquiry.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Comment;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
public class ResInfoRegistrationParam {

    @Comment("이미지 파일 리스트")
    private MultipartFile imgFile;

    @Comment("공간 id")
    private int roomType;

    @Comment("사진 설명")
    private String content;
}

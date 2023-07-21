package com.cn.interri.design.reply.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Comment;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
public class ReplyInfoRegistParam {

    @Comment("이미지 파일")
    private MultipartFile imgFile;

    @Comment("공간 id")
    private String roomType;

    @Comment("사진 설명")
    private String content;
}

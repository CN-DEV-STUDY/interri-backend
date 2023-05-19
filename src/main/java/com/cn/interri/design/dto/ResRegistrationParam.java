package com.cn.interri.design.dto;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
public class ResRegistrationParam {
    @Comment("글 등록 사용자 id")
    private Long userId;

    @Comment("가격 합계")
    private int price;

    @Comment("디자인 응답 목록")
    private ResInfoRegistrationParam[] params;

}

package com.cn.interri.design.inquiry.dto;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.util.List;

@Getter
@Setter
@ToString
public class ResRegistrationParam {
    @Comment("글 등록 사용자 id")
    private long userId;

    @Comment("가격 합계")
    private int price;

    @Comment("디자인 응답 목록")
    private List<ResInfoRegistrationParam> params;

}

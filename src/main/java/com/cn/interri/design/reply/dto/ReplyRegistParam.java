package com.cn.interri.design.reply.dto;

import com.cn.interri.design.inquiry.dto.ResInfoRegistrationParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import lombok.*;
import org.hibernate.annotations.Comment;

import java.util.List;

@Getter
@Setter
@ToString
public class ReplyRegistParam {
    @Comment("글 등록 사용자 id")
    private long userId;

    @Comment("가격 합계")
    private int price;

    @Comment("디자인 응답 목록")
    private List<ReplyInfoRegistrationParam> params;

}

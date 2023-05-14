package com.cn.interri.design.dto;
import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.Comment;

import java.util.List;

@Getter
public class ResRegistrationParam {

    @Comment("가격 합계")
    private int totalPrice;

    @Comment("디자인 응답 정보 상세")
    private List<ResInfoRegistrationParam> infoRegistrationParam;

}

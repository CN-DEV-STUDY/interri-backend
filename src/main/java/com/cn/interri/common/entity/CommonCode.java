package com.cn.interri.common.entity;

import com.cn.interri.common.enums.CodeType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class CommonCode {

    @Id
    @Column(name = "common_code_id")
    private String id;

    @Enumerated(EnumType.STRING)
    private CodeType codeType;
    private String codeNm;
    private String codeExp;
    private Integer codeSort;
    private String useYn;

    @OneToMany(mappedBy = "commonCode")
    private List<CommonCodeDesign> commonTypeDesigns = new ArrayList<>();

}

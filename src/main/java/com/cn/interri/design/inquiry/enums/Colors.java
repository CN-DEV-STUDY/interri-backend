package com.cn.interri.design.inquiry.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 메인, 서브 컬러 enum
 */
public enum Colors {
    BLACK,
    WHITE,
    BEIGE,
    ETC;

    /**
     * Colors[] -> List<String>
     */
    public static List<String> getList() {
        return Arrays.stream(Colors.values())
                .map(String::valueOf)
                .collect(Collectors.toList());
    }
}

package com.cn.interri.design.enums;

import com.cn.interri.design.inquiry.enums.Colors;
import org.junit.jupiter.api.Test;

class MainColorsTest {

    @Test
    void values() {
        for(Colors c : Colors.values())
            System.out.println(c);
    }
}
package com.cn.interri.design.constant;

import org.junit.jupiter.api.Test;

class MainColorsTest {

    @Test
    void values() {
        for(Colors c : Colors.values())
            System.out.println(c);
    }
}
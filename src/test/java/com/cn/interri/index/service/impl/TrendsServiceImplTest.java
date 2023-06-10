package com.cn.interri.index.service.impl;

import com.cn.interri.index.service.TrendsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TrendsServiceImplTest {

    @Autowired
    TrendsService trendsService;

    @Test
    void getIndex() {

        trendsService.getIndex();
    }
}
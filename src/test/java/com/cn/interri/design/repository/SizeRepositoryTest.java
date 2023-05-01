package com.cn.interri.design.repository;

import com.cn.interri.design.domain.Size;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class SizeRepositoryTest {

    @Autowired
    private SizeRepository sizeRepository;

    @Test
    void testFindAll_success() {
        List<Size> sizeList = sizeRepository.findAll();

        assertThat(sizeList)
                .isNotNull()
                .hasSize(6);
    }

}
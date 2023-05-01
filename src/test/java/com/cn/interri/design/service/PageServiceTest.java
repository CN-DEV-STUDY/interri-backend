package com.cn.interri.design.service;

import com.cn.interri.design.dto.ReqRegistrationResource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PageServiceTest {

    @Autowired PageService pageService;

    @DisplayName("디자인 요청 등록 페이지에 필요한 자원을 조회한다.")
    @Test
    void getRegistrationPageResource() {
        ReqRegistrationResource resource = pageService.getRegistrationPageResource();

        // 10평 미만, 10평대, 20평대, 30평대, 40평대, 50평 이상
        assertThat(resource.getSizeList())
                .isNotNull()
                .hasSize(6);

        // 원룸&오피스텔, 아파트, 빌라&연립, 단독주택, 사무공간, 상업공간, 기타
        assertThat(resource.getHousingTypeList())
                .isNotNull()
                .hasSize(7);

        // TODO 컬러 테스트
//        assertThat(resource.getColorList())
//                .isNotNull()
//                .hasSize()

        assertThat(resource.getRoomTypeList())
                .isNotNull()
                .hasSize(14);

        assertThat(resource.getStyleList())
                .isNotNull()
                .hasSize(8);
    }
}
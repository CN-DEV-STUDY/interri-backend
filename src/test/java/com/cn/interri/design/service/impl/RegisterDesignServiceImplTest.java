package com.cn.interri.design.service.impl;

import com.cn.interri.design.dto.ReqRegistrationParam;
import com.cn.interri.design.service.RegisterDesignService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@SpringBootTest
@Transactional
class RegisterDesignServiceImplTest {

    @Autowired
    private RegisterDesignService registerDesignService;

    public ReqRegistrationParam createReqRegistParam() {
        ReqRegistrationParam param = new ReqRegistrationParam();

        return param;
    }


    @DisplayName("디자인 요청 등록 시 유저 아이디 없음")
    @Test
    void 디자인_요청_등록_유저_아이디_없음() throws Exception {
        // given
        ReqRegistrationParam param = createReqRegistParam();

        // when
        Assertions.assertThrows(EntityNotFoundException.class, () -> registerDesignService.saveDesignRequest(param));
    }

    @DisplayName("디자인 요청 등록 시 유저 아이디 없음")
    @Test
    void 디자인_요청_등록_이미지_공간정보_내_없음() throws Exception {
        // given
        ReqRegistrationParam param = createReqRegistParam();

        // when
        Assertions.assertThrows(Exception.class, () -> registerDesignService.saveDesignRequest(param));
    }
}
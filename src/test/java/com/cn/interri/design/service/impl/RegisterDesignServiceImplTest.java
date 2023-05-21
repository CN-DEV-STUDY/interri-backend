package com.cn.interri.design.service.impl;

import com.cn.interri.design.dto.ReqRegistrationDto;
import com.cn.interri.design.dto.ReqRegistrationParam;
import com.cn.interri.design.service.RegisterDesignService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
class RegisterDesignServiceImplTest {

    @Autowired
    private RegisterDesignService registerDesignService;

    public ReqRegistrationParam createReqRegistParam() {
        ReqRegistrationParam param = new ReqRegistrationParam();
        param.setUserId(1);
        param.setSizeId(1);
        param.setHousingTypeId(1);
        param.setMainColor("BLACK");
        param.setSubColor("BLACK");
        param.setMaxPrice(1000000);
        param.setDueDate(LocalDate.now());
        param.setStyleId(1);
        param.setTempYn("N");

        List<ReqRegistrationDto> reqRegistrationDtoList = new ArrayList<>();
        List<MultipartFile> multipartFiles = new ArrayList<>();
        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );
        multipartFiles.add(file);
        ReqRegistrationDto dto = new ReqRegistrationDto();
        dto.setMultipartFiles(multipartFiles);
        dto.setRoomTypeId(1);
        dto.setContent("사진 설명 ㅋ");

        reqRegistrationDtoList.add(dto);
        param.setReqRegistrationDtoList(reqRegistrationDtoList);


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

    @DisplayName("정상 등록 케이스")
    @Test
    void register() throws Exception {
        registerDesignService.saveDesignRequest(createReqRegistParam());
    }
}
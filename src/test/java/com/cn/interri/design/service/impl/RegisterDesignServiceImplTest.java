package com.cn.interri.design.service.impl;

import com.cn.interri.design.inquiry.dto.DesignRequestInfo;
import com.cn.interri.design.inquiry.dto.RegistReqDto;
import com.cn.interri.design.inquiry.service.DesignInquiryService;
import com.cn.interri.design.reply.dto.ReplyRegistParam;
import com.cn.interri.design.reply.service.DesignRegisterReplyService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
class RegisterDesignServiceImplTest {


    @Autowired
    private DesignInquiryService designInquiryService;

    @Autowired
    private DesignRegisterReplyService registerReplyService;

    public RegistReqDto createReqRegistParam() {
        RegistReqDto param = new RegistReqDto();
//        param.setUserId(1);
//        param.setSizeId(1);
//        param.setHousingTypeId(1);
        param.setMainColor("BLACK");
        param.setSubColor("BLACK");
        param.setMaxPrice(1000000);
        param.setDueDate(LocalDate.now());
//        param.setStyleId(1);
        param.setTempYn("N");

        List<DesignRequestInfo> reqRegistrationDtoList = new ArrayList<>();
        List<MultipartFile> multipartFiles = new ArrayList<>();
        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );
        multipartFiles.add(file);
        DesignRequestInfo dto = new DesignRequestInfo();
//        dto.setImages(multipartFiles);
//        dto.setRoomTypeId(1);
        dto.setContent("사진 설명 ㅋ");

        reqRegistrationDtoList.add(dto);
        param.setDesignRequestInfos(reqRegistrationDtoList);


        return param;
    }

    public ReplyRegistParam createResRegistParam(){
        ReplyRegistParam param = new ReplyRegistParam();
//        ReplyInfoRegistrationParam infoParam = new ReplyInfoRegistrationParam();

        param.setUserId(1);
        param.setPrice(170000);


//        List<ReplyInfoRegistrationParam> resInfoList = new ArrayList<>();

        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );

//        infoParam.setContent("원룸은 이렇게 꾸미는 거예yo!");
//        infoParam.setRoomType(1);
//        infoParam.setImgFile(file);
//        resInfoList.add(infoParam);

//        param.setParams(resInfoList);

        return param;
    }

    @DisplayName("디자인 요청 등록 시 유저 아이디 없음")
    @Test
    void 디자인_요청_등록_유저_아이디_없음() throws Exception {
        // given
        RegistReqDto param = createReqRegistParam();

        // when
        Assertions.assertThrows(EntityNotFoundException.class, () -> designInquiryService.saveDesignInquiry(param));
    }

    @DisplayName("디자인 요청 등록 시 유저 아이디 없음")
    @Test
    void 디자인_요청_등록_이미지_공간정보_내_없음() throws Exception {
        // given
        RegistReqDto param = createReqRegistParam();

        // when
        Assertions.assertThrows(Exception.class, () -> designInquiryService.saveDesignInquiry(param));
    }

    @DisplayName("디자인 요청 정상 등록 케이스")
    @Test
    void reqRegister() throws Exception {
        designInquiryService.saveDesignInquiry(createReqRegistParam());
    }

    @DisplayName("디자인 응답 정상 등록 케이스")
    @Test
    void resRegister() throws Exception {
        registerReplyService.saveDesignReply(1,createResRegistParam());
    }
}
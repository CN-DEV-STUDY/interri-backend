package com.cn.interri.design.inquiry.service.impl;

import com.cn.interri.common.exception.exceptions.EmptyFileException;
import com.cn.interri.common.exception.exceptions.FileUploadFailedException;
import com.cn.interri.common.service.FileService;
import com.cn.interri.common.utils.SecurityUtil;
import com.cn.interri.design.inquiry.dto.ResInfoRegistrationParam;
import com.cn.interri.design.inquiry.dto.ResRegistrationParam;
import com.cn.interri.design.inquiry.dto.RegistReqDto;
import com.cn.interri.design.inquiry.dto.DesignRequestInfo;
import com.cn.interri.design.reply.dto.ResInfoRegistrationParam;
import com.cn.interri.design.reply.dto.ResRegistrationParam;
import com.cn.interri.design.inquiry.entity.DesignReq;
import com.cn.interri.design.inquiry.entity.DesignRes;
import com.cn.interri.design.inquiry.entity.DesignResInfo;
import com.cn.interri.design.inquiry.repository.DesignReqRepository;
import com.cn.interri.design.inquiry.repository.custom.DesignResRepository;
import com.cn.interri.design.inquiry.service.RegisterService;
import com.cn.interri.user.entity.User.User;
import com.cn.interri.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static com.cn.interri.design.inquiry.entity.DesignRes.createDesignRes;
import static com.cn.interri.design.inquiry.entity.DesignResInfo.createDesignResInfo;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class RegisterServiceImpl implements RegisterService {
    private final DesignReqRepository designReqRepository;
    private final DesignResRepository designResRepository;
    private final UserRepository userRepository;
    private final FileService fileService;



    private final String RESPONSE = "response/";

    @Override
    @Transactional
    public void saveDesignInquiry(DesignReq designReq) {
        designReqRepository.save(designReq);
    }

    @Override
    @Transactional
    public void saveDesignResponse(long designReqId, ResRegistrationParam res) throws Exception{

        User user = getUser(); // 글 등록자

        List<DesignResInfo> designResInfoList  = new ArrayList<>();

        for (ResInfoRegistrationParam info : res.getParams()) {
            uploadFiles(info.getImgFile(), RESPONSE); // s3에 이미지 업로드

            DesignResInfo resInfo = createDesignResInfo(info.getContent(), "N", null); // createFileDesignRes(info.getImgFile(), RESPONSE)
            designResInfoList.add(resInfo);
        }

        DesignRes designRes = createDesignRes(designReqId, user, res.getPrice(), "N", designResInfoList);
        designResRepository.save(designRes);
    }

    private void uploadFiles(MultipartFile multipartFile , String purpose) throws FileUploadFailedException, EmptyFileException {
        fileService.uploadFile(multipartFile , purpose);
    }

    private User getUser() {
        return userRepository.findByEmail(SecurityUtil.getCurrentUserEmail())
                .orElseThrow(EntityNotFoundException::new);
    }
}

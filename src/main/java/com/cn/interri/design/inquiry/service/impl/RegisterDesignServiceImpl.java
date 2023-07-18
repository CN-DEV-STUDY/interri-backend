package com.cn.interri.design.inquiry.service.impl;

import com.cn.interri.common.repository.CommonTypeRepository;
import com.cn.interri.common.service.FileService;
import com.cn.interri.common.utils.SecurityUtil;
import com.cn.interri.design.inquiry.dto.RegistReqDto;
import com.cn.interri.design.inquiry.dto.DesignRequestInfo;
import com.cn.interri.design.reply.dto.ResInfoRegistrationParam;
import com.cn.interri.design.reply.dto.ResRegistrationParam;
import com.cn.interri.design.inquiry.entity.DesignReq;
import com.cn.interri.design.inquiry.entity.DesignReqInfo;
import com.cn.interri.design.inquiry.entity.DesignRes;
import com.cn.interri.design.inquiry.entity.DesignResInfo;
import com.cn.interri.design.inquiry.repository.DesignReqRepository;
import com.cn.interri.design.inquiry.repository.DesignResInfoRepository;
import com.cn.interri.design.inquiry.repository.custom.DesignResRepository;
import com.cn.interri.design.inquiry.service.RegisterDesignService;
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
import static com.cn.interri.design.inquiry.entity.FileDesignReq.createFileDesignReq;
import static com.cn.interri.design.inquiry.entity.FileDesignRes.createFileDesignRes;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class RegisterDesignServiceImpl implements RegisterDesignService {
    private final DesignReqRepository designReqRepository;
    private final DesignResRepository designResRepository;
    private final DesignResInfoRepository designResInfoRepository;

    private final UserRepository userRepository;
    private final CommonTypeRepository commonTypeRepository;
    private final FileService fileService;


    private final String REQUEST="request/";
    private final String RESPONSE = "response/";


    @Override
    @Transactional
    public void saveDesignRequest(RegistReqDto req) throws Exception {

        // TODO: EntityNotFoundException > Exception Handler에 추가
        User user = getUser();

        List<DesignReqInfo> designReqInfoList = new ArrayList<>();
        for (DesignRequestInfo reqDto : req.getDesignRequestInfos()) {
//            uploadFiles(reqDto.getImages() , REQUEST);

            // TODO: controller에서 파라미터에 대한 유효성 검사 필요
            DesignReqInfo designReqInfo = new DesignReqInfo(reqDto.getContent(), "N", createFileDesignReq(reqDto.getImage()));
            designReqInfoList.add(designReqInfo);
        }

        DesignReq designReq = new DesignReq(req.getMainColor(), req.getSubColor(), req.getMaxPrice(), req.getDueDate(), req.getTempYn(), "N", user, designReqInfoList);

        designReqRepository.save(designReq);
    }

    @Override
    @Transactional
    public void saveDesignResponse(long designReqId, ResRegistrationParam res) throws Exception{

        User user = getUser(); // 글 등록자

        List<DesignResInfo> designResInfoList  = new ArrayList<>();

        for (ResInfoRegistrationParam info : res.getParams()) {
            uploadFiles(info.getImgFiles(), RESPONSE); // s3에 이미지 업로드

            DesignResInfo resInfo = createDesignResInfo(info.getContent(), "N", createFileDesignRes(info.getImgFiles(), RESPONSE));
            designResInfoList.add(resInfo);
        }

        DesignRes designRes = createDesignRes(designReqId, user, res.getPrice(), "N", designResInfoList);
        designResRepository.save(designRes);
    }

    private void uploadFiles(List<MultipartFile> multipartFiles , String purpose) {
        for (MultipartFile multipartFile : multipartFiles) {
            fileService.uploadFile(multipartFile , purpose);
        }
    }

    private User getUser() {
        return userRepository.findByEmail(SecurityUtil.getCurrentUserEmail())
                .orElseThrow(EntityNotFoundException::new);
    }

}

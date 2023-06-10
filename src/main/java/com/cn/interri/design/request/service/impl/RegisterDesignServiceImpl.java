package com.cn.interri.design.request.service.impl;

import com.cn.interri.common.entity.HousingType;
import com.cn.interri.common.entity.RoomType;
import com.cn.interri.common.entity.Size;
import com.cn.interri.common.entity.Style;
import com.cn.interri.common.repository.HousingTypeRepository;
import com.cn.interri.common.repository.RoomTypeRepository;
import com.cn.interri.common.repository.SizeRepository;
import com.cn.interri.common.repository.StyleRepository;
import com.cn.interri.common.service.FileService;
import com.cn.interri.design.request.entity.*;
import com.cn.interri.design.request.dto.ReqRegistrationDto;
import com.cn.interri.design.request.dto.ReqRegistrationParam;
import com.cn.interri.design.request.dto.ResInfoRegistrationParam;
import com.cn.interri.design.request.dto.ResRegistrationParam;
import com.cn.interri.design.request.repository.*;
import com.cn.interri.design.request.repository.custom.DesignResRepository;
import com.cn.interri.design.request.service.RegisterDesignService;
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

import static com.cn.interri.design.request.entity.DesignRes.createDesignRes;
import static com.cn.interri.design.request.entity.DesignResInfo.createDesignResInfo;
import static com.cn.interri.design.request.entity.FileDesignReq.createFileDesignReq;
import static com.cn.interri.design.request.entity.FileDesignRes.*;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class RegisterDesignServiceImpl implements RegisterDesignService {
    private final DesignReqRepository designReqRepository;
    private final DesignResRepository designResRepository;
    private final DesignResInfoRepository designResInfoRepository;

    private final UserRepository userRepository;
    private final HousingTypeRepository housingTypeRepository;
    private final StyleRepository styleRepository;
    private final SizeRepository sizeRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final FileService fileService;


    private final String REQUEST="request/";
    private final String RESPONSE = "response/";


    @Override
    @Transactional
    public void saveDesignRequest(ReqRegistrationParam req) throws Exception {

        // TODO: EntityNotFoundException > Exception Handler에 추가
        User user = getUser(req.getUserId());
        Size size = getSize(req.getSizeId());
        HousingType housingType = getHousingType(req.getHousingTypeId());
        Style style = getStyle(req.getStyleId());

        List<DesignReqInfo> designReqInfoList = new ArrayList<>();
        for (ReqRegistrationDto reqDto : req.getReqRegistrationDtoList()) {
            uploadFiles(reqDto.getMultipartFiles() , REQUEST);

            // TODO: controller에서 파라미터에 대한 유효성 검사 필요
            DesignReqInfo designReqInfo = new DesignReqInfo(reqDto.getContent(), "N", getRoomType(reqDto.getRoomTypeId()), createFileDesignReq(reqDto.getMultipartFiles()));
            designReqInfoList.add(designReqInfo);
        }

        DesignReq designReq = new DesignReq(req.getMainColor(), req.getSubColor(), req.getMaxPrice(), req.getDueDate(), req.getTempYn(), "N", user, designReqInfoList, housingType, style, size);

        designReqRepository.save(designReq);
    }

    @Override
    @Transactional
    public void saveDesignResponse(long designReqId, ResRegistrationParam res) throws Exception{

        User user = getUser(res.getUserId()); // 글 등록자

        List<DesignResInfo> designResInfoList  = new ArrayList<>();

        for (ResInfoRegistrationParam info : res.getParams()) {
            uploadFiles(info.getImgFiles(), RESPONSE); // s3에 이미지 업로드

            DesignResInfo resInfo = createDesignResInfo(info.getContent(), "N", getRoomType(info.getRoomType()), createFileDesignRes(info.getImgFiles(), RESPONSE));
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

    private RoomType getRoomType(int roomTypeId) {
        return roomTypeRepository.findById(roomTypeId)
                .orElseThrow(EntityNotFoundException::new);
    }

    private User getUser(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(EntityNotFoundException::new);
    }

    private HousingType getHousingType(int housingTypeId) {
        return housingTypeRepository.findById(housingTypeId)
                .orElseThrow(EntityNotFoundException::new);
    }

    private Style getStyle(int styleId) {
        return styleRepository.findById(styleId)
                .orElseThrow(EntityNotFoundException::new);
    }

    private Size getSize(int sizeId) {
        return sizeRepository.findById(sizeId)
                .orElseThrow(EntityNotFoundException::new);
    }
}

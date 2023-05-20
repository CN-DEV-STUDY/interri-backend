package com.cn.interri.design.service.impl;

import com.cn.interri.common.service.FileService;
import com.cn.interri.design.domain.*;
import com.cn.interri.design.dto.ReqRegistrationDto;
import com.cn.interri.design.dto.ReqRegistrationParam;
import com.cn.interri.design.dto.ResRegistrationParam;
import com.cn.interri.design.repository.*;
import com.cn.interri.design.service.RegisterDesignService;
import com.cn.interri.user.domain.User.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static com.cn.interri.design.domain.FileDesignReq.createFileDesignReq;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterDesignServiceImpl implements RegisterDesignService {
    private final DesignReqRepository designReqRepository;

    private final UserRepository userRepository;
    private final HousingTypeRepository housingTypeRepository;
    private final StyleRepository styleRepository;
    private final SizeRepository sizeRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final FileService fileService;

    @Override
    public void saveDesignRequest(ReqRegistrationParam req) throws Exception {

        // TODO: EntityNotFoundException > Exception Handler에 추가
        User user = getUser(req.getUserId());
        Size size = getSize(req.getSizeId());
        HousingType housingType = getHousingType(req.getHousingTypeId());
        Style style = getStyle(req.getStyleId());

        List<DesignReqInfo> designReqInfoList = null;
        for (ReqRegistrationDto reqDto : req.getReqRegistrationDtoList()) {
            uploadFiles(reqDto.getMultipartFiles());

            // TODO: controller에서 파라미터에 대한 유효성 검사 필요
            DesignReqInfo designReqInfo = DesignReqInfo.builder()
                    .content(reqDto.getContent())
                    .roomType(getRoomType(reqDto.getRoomTypeId()))
                    .fileDesignReqList(createFileDesignReq(reqDto.getMultipartFiles()))
                    .build();
            designReqInfoList.add(designReqInfo);
        }

        DesignReq designReq = DesignReq.builder()
                .mainColor(req.getMainColor())
                .subColor(req.getSubColor())
                .maxPrice(req.getMaxPrice())
                .dueDate(req.getDueDate())
                .tempYn(req.getTempYn())
                .user(user)
                .designReqInfoList(designReqInfoList)
                .housingType(housingType)
                .style(style)
                .size(size)
                .build();

        designReqRepository.save(designReq);
    }

    private void uploadFiles(List<MultipartFile> multipartFiles) {
        for (MultipartFile multipartFile : multipartFiles) {
            fileService.uploadFile(multipartFile);
        }
    }

    private RoomType getRoomType(long roomTypeId) {
        return roomTypeRepository.findById(roomTypeId)
                .orElseThrow(EntityNotFoundException::new);
    }

    private User getUser(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(EntityNotFoundException::new);
    }

    private HousingType getHousingType(long housingTypeId) {
        return housingTypeRepository.findById(housingTypeId)
                .orElseThrow(EntityNotFoundException::new);
    }

    private Style getStyle(long styleId) {
        return styleRepository.findById(styleId)
                .orElseThrow(EntityNotFoundException::new);
    }

    private Size getSize(long sizeId) {
        return sizeRepository.findById(sizeId)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void saveDesignResponse(Long designReqId, ResRegistrationParam res) {

    }
}

package com.cn.interri.design.service.impl;

import com.cn.interri.common.service.FileService;
import com.cn.interri.design.domain.*;
import com.cn.interri.design.dto.ReqRegistrationParam;
import com.cn.interri.design.repository.*;
import com.cn.interri.design.service.RegisterDesignService;
import com.cn.interri.user.domain.User.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterDesignServiceImpl implements RegisterDesignService {



    private final UserRepository userRepository;
    private final HousingTypeRepository housingTypeRepository;
    private final StyleRepository styleRepository;
    private final SizeRepository sizeRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final FileService fileService;

    @Override
    public void saveDesignRequest(ReqRegistrationParam req) {

        // TODO: EntityNotFoundException > Exception Handler에 추가
        User user = getUser(req.getUserId());
        HousingType housingType = getHousingType(req.getHousingTypeId());
        Style style = getStyle(req.getStyleId());
        Size size = getSize(req.getSizeId());

        List<DesignReqInfo> designReqInfos = new ArrayList<>();
        for (int i = 0; i < req.getMultipartFiles().size(); i++) {
            DesignReqInfo designReqInfo = new DesignReqInfo(req.getContent().get(i));
            RoomType roomType = getRoomType(req.getRoomTypes().get(i));


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

    /**
     * 임시 저장
     */
    @Override
    public void saveDesignRequestTemp() {

    }

    @Override
    public void saveDesignResponse() {

    }
}

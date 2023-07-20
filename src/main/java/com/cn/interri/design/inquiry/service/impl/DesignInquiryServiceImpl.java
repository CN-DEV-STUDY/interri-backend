package com.cn.interri.design.inquiry.service.impl;

import com.cn.interri.common.exception.exceptions.EmptyFileException;
import com.cn.interri.common.exception.exceptions.FileUploadFailedException;
import com.cn.interri.common.service.FileService;
import com.cn.interri.common.utils.SecurityUtil;
import com.cn.interri.design.inquiry.dto.DesignRequestInfo;
import com.cn.interri.design.inquiry.dto.RegistReqDto;
import com.cn.interri.design.inquiry.entity.DesignReq;
import com.cn.interri.design.inquiry.entity.DesignReqInfo;
import com.cn.interri.design.inquiry.service.DesignInquiryService;
import com.cn.interri.design.inquiry.service.RegisterService;
import com.cn.interri.user.entity.User.User;
import com.cn.interri.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static com.cn.interri.design.inquiry.entity.FileDesignReq.createFileDesignReq;

@RequiredArgsConstructor
@Service
public class DesignInquiryServiceImpl implements DesignInquiryService {

    private final FileService fileService;
    private final RegisterService registerService;
    private final UserRepository userRepository;

    private final String REQUEST="request/";

    @Override
    public void saveDesignInquiry(RegistReqDto req) throws Exception {
        // TODO: EntityNotFoundException > Exception Handler에 추가
        User user = getUser();

        List<DesignReqInfo> designReqInfoList = new ArrayList<>();

        for (DesignRequestInfo designRequestInfo : req.getDesignRequestInfos()) {
            uploadFiles(designRequestInfo.getImage() , REQUEST);
            designReqInfoList.add(DesignReqInfo.builder()
                                        .content(designRequestInfo.getContent())
                                        .fileDesignReq(createFileDesignReq(designRequestInfo.getImage()))
                                        .build());
        }

        DesignReq designReq = DesignReq.builder()
                .mainColor(req.getMainColor())
                .subColor(req.getSubColor())
                .maxPrice(req.getMaxPrice())
                .dueDate(req.getDueDate())
                .tempYn(req.getTempYn())
                .user(user)
                .designReqInfoList(designReqInfoList)
                .build();

        registerService.saveDesignInquiry(designReq);
    }

    private void uploadFiles(MultipartFile multipartFile , String purpose) throws FileUploadFailedException, EmptyFileException {
        fileService.uploadFile(multipartFile , purpose);
    }

    private User getUser() {
        return userRepository.findByEmail(SecurityUtil.getCurrentUserEmail())
                .orElseThrow(EntityNotFoundException::new);
    }
}

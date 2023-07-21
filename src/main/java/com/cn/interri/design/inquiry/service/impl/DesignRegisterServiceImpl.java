package com.cn.interri.design.inquiry.service.impl;

import com.cn.interri.common.service.FileService;
import com.cn.interri.common.utils.SecurityUtil;
import com.cn.interri.design.inquiry.entity.DesignReq;
import com.cn.interri.design.inquiry.repository.DesignReqRepository;
import com.cn.interri.user.entity.User.User;
import com.cn.interri.design.inquiry.service.DesignRegisterService;
import com.cn.interri.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class DesignRegisterServiceImpl implements DesignRegisterService {
    private final DesignReqRepository designReqRepository;

    private final UserRepository userRepository;
    private final FileService fileService;


    private final String REQUEST="request/";


    @Override
    @Transactional
    public void saveDesignInquiry(DesignReq designReq) {
        designReqRepository.save(designReq);
    }

    private User getUser() {
        return userRepository.findByEmail(SecurityUtil.getCurrentUserEmail())
                .orElseThrow(EntityNotFoundException::new);
    }

}

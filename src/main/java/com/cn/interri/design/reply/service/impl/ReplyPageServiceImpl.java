package com.cn.interri.design.reply.service.impl;

import com.cn.interri.common.repository.CommonTypeDesignRepository;
import com.cn.interri.common.repository.CommonTypeRepository;
import com.cn.interri.design.reply.service.ReplyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReplyPageServiceImpl implements ReplyPageService {

    private final CommonTypeDesignRepository commonTypeDesignRepository;
    private final CommonTypeRepository commonTypeRepository;

    @Override
    public List<String> getResRoomTypeNm(Long id) {

        List<String> commonCodeDesignIdList = commonTypeDesignRepository.getDesignInquiryCommonCode(id);
        List<String> roomTypeNmList = commonTypeRepository.getDesignInquiryRoomList(commonCodeDesignIdList);

        return roomTypeNmList;

    }
}

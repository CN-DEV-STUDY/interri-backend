package com.cn.interri.design.reply.service.impl;

import com.cn.interri.common.exception.exceptions.EmptyFileException;
import com.cn.interri.common.exception.exceptions.FileUploadFailedException;
import com.cn.interri.common.repository.CommonTypeRepository;
import com.cn.interri.common.service.FileService;
import com.cn.interri.common.utils.SecurityUtil;
import com.cn.interri.design.reply.dto.ReplyInfoRegistrationParam;
import com.cn.interri.design.reply.entity.DesignReply;
import com.cn.interri.design.reply.entity.DesignReplyInfo;
import com.cn.interri.design.reply.dto.ReplyRegistParam;
import com.cn.interri.design.reply.repository.DesignResInfoRepository;
import com.cn.interri.design.reply.repository.DesignReplyRepository;
import com.cn.interri.design.reply.service.DesignRegisterReplyService;
import com.cn.interri.user.entity.User.User;
import com.cn.interri.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static com.cn.interri.design.reply.entity.DesignReply.createDesignReply;
import static com.cn.interri.design.reply.entity.DesignReplyInfo.createDesignReplyInfo;
import static com.cn.interri.design.reply.entity.FileDesignReply.createFileDesignRes;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DesignRegisterReplyServiceImpl implements DesignRegisterReplyService {

    private final DesignReplyRepository designResRepository;
    private final DesignResInfoRepository designResInfoRepository;
    private final CommonTypeRepository commonTypeRepository;
    private final UserRepository userRepository;
    private final FileService fileService;
    private final String RESPONSE = "response/";


    @Override
    @Transactional
    public void saveDesignResponse(long designReqId, ReplyRegistParam res) throws Exception{

        User user = getUser(); // 글 등록자

        List<DesignReplyInfo> designResInfoList  = new ArrayList<>();

        for (ReplyInfoRegistrationParam info : res.getParams()) {
            uploadFiles(info.getImgFile(), RESPONSE); // s3에 이미지 업로드

            DesignReplyInfo resInfo = createDesignReplyInfo(info.getContent(), "N", createFileDesignRes(info.getImgFile(), RESPONSE));
            designResInfoList.add(resInfo);
        }

        DesignReply designRes = createDesignReply(designReqId, user, res.getPrice(), "N", designResInfoList);
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

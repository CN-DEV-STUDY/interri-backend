package com.cn.interri.design.inquiry.service.impl;

import com.amazonaws.services.s3.AmazonS3Client;
import com.cn.interri.common.dto.HousingTypeDto;
import com.cn.interri.common.dto.RoomTypeDto;
import com.cn.interri.common.dto.SizeDto;
import com.cn.interri.common.dto.StyleDto;
import com.cn.interri.common.entity.CommonCode;
import com.cn.interri.common.enums.CodeType;
import com.cn.interri.common.repository.CommonTypeDesignRepository;
import com.cn.interri.common.repository.CommonTypeRepository;
import com.cn.interri.design.inquiry.dto.ReqDetailReqResource;
import com.cn.interri.design.inquiry.dto.ReqDetailResResource;
import com.cn.interri.design.inquiry.dto.ReqInfoDetailResource;
import com.cn.interri.design.inquiry.dto.ReqRegistrationResource;
import com.cn.interri.design.reply.entity.DesignReplyInfo;
import com.cn.interri.design.inquiry.entity.FileDesignReq;
import com.cn.interri.design.reply.entity.FileDesignReply;
import com.cn.interri.design.inquiry.enums.Colors;
import com.cn.interri.design.inquiry.repository.*;
import com.cn.interri.design.reply.repository.DesignReplyRepository;
import com.cn.interri.design.inquiry.service.PageService;
import com.cn.interri.design.reply.repository.DesignReplyInfoRepository;
import com.cn.interri.design.reply.repository.FileDesignReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PageServiceImpl implements PageService {

    private final DesignReqRepository designReqRepository;
    private final DesignReqInfoRepository designReqInfoRepository;
    private final DesignReplyInfoRepository designResInfoRepository;
    private final DesignReplyRepository designResRepository;
    private final FileDesignReplyRepository fileDesignResRepository;
    private final FileDesignReqRepository fileDesignReqRepository;
    private final CommonTypeDesignRepository commonTypeDesignRepository;
    private final CommonTypeRepository commonTypeRepository;

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    @Override
    public ReqRegistrationResource getRegistrationPageResource() {
        List<CommonCode> commonTypes = commonTypeRepository.findAll();

        // 평수
        List<SizeDto> sizeDtoList = commonTypes.stream()
                        .filter(commonCode -> commonCode.getCodeType() == CodeType.SIZE)
                        .map(commonCode -> new SizeDto(commonCode.getId(), commonCode.getCodeNm()))
                        .toList();

        // 주거 형태
        List<HousingTypeDto> housingTypeList = commonTypes.stream()
                        .filter(commonCode -> commonCode.getCodeType() == CodeType.HOUSING_TYPE)
                        .map(commonCode -> new HousingTypeDto(commonCode.getId(), commonCode.getCodeNm()))
                        .toList();
        // 메인, 서브 컬러
        List<String> colorList = Colors.getList();
        // 공간
        List<RoomTypeDto> roomTypeDtoList = commonTypes.stream()
                        .filter(commonCode -> commonCode.getCodeType() == CodeType.ROOM_TYPE)
                        .map(commonCode -> new RoomTypeDto(commonCode.getId(), commonCode.getCodeNm()))
                        .toList();
        // 스타일
        List<StyleDto> styleList = commonTypes.stream()
                        .filter(commonCode -> commonCode.getCodeType() == CodeType.STYLE)
                        .map(commonCode -> new StyleDto(commonCode.getId(), commonCode.getCodeNm()))
                        .toList();

        return new ReqRegistrationResource(sizeDtoList,
                housingTypeList,
                colorList,
                roomTypeDtoList,
                styleList);
    }

    @Override
    public ReqDetailReqResource getDesignReqDetails(Long id,String sortType) {

        // 디자인 요청 내용
        ReqDetailReqResource reqDetail = designReqRepository.getReqDetail(id);
        List<ReqInfoDetailResource> reqInfoDetail = designReqInfoRepository.getReqInfoDetail(id);

        // 디자인 요청 상세 정보는 여러 개가 올 수 있으며, 상세 정보에서도 이미지가 여러 개 올 수있다.
        reqInfoDetail.stream().peek(req->{
            FileDesignReq file = fileDesignReqRepository.findByDesignReqInfo_IdAndDelYn(req.getInfoId(), "N"); // 디자인 요청 상세에 맞는 file을 가져온다.

//            req.setImgPathList(amazonS3Client.getUrl(bucketName, file.getFilePath()).toString()); // dto의 imageList를 s3 bucket 경로로 바뀐 데이터로 업데이트 한다.
        }).toList();


        // 디자인 요청에 대한 답변 내용
        List<ReqDetailResResource> reqDetailRes = designResRepository.getReqDetailRes(id , sortType);

        List<ReqDetailResResource> reqDetailResList = reqDetailRes.stream().peek(res -> {
            DesignReplyInfo replyInfo = designResInfoRepository.findTopByDesignReply_IdAndDelYn(res.getId(), "N");
            FileDesignReply fileDesignRes = fileDesignResRepository.findTopByDesignReplyInfo_IdAndDelYn(replyInfo.getId(),"N");

//            res.setRepImgPath(amazonS3Client.getUrl(bucketName,fileDesignRes.getFilePath()).toString());
        }).collect(Collectors.toList());

        reqDetail.setReqInfoDetailResources(reqInfoDetail);
        reqDetail.setReqDetailResResources(reqDetailResList);

        return reqDetail;
    }

}

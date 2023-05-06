package com.cn.interri.design.service.impl;

import com.amazonaws.services.s3.AmazonS3Client;
import com.cn.interri.design.domain.FileDesignRes;
import com.cn.interri.design.dto.*;
import com.cn.interri.design.enums.Colors;
import com.cn.interri.design.repository.*;
import com.cn.interri.design.repository.custom.DesignResRepository;
import com.cn.interri.design.service.PageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PageServiceImpl implements PageService {

    private final SizeRepository sizeRepository;
    private final HousingTypeRepository housingTypeRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final StyleRepository styleRepository;
    private final DesignReqRepository designReqRepository;
    private final DesignReqInfoRepository designReqInfoRepository;
    private final DesignResRepository designResRepository;
    private final FileDesignResRepository fileDesignResRepository;

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Override
    public ReqRegistrationResource getRegistrationPageResource() {
        // 평수
        List<SizeDto> sizeDtoList = sizeRepository.findAllSize();
        // 주거 형태
        List<HousingTypeDto> housingTypeList = housingTypeRepository.findAllHousingType();
        // 메인, 서브 컬러
        List<String> colorList = Colors.getList();
        // 공간
        List<RoomTypeDto> roomTypeDtoList = roomTypeRepository.findAllRoomType();
        // 스타일
        List<StyleDto> styleList = styleRepository.findAllStyle();

        return new ReqRegistrationResource(sizeDtoList,
                housingTypeList,
                colorList,
                roomTypeDtoList,
                styleList);
    }

    @Override
    public ReqDetailReqResource getDesignReqDetails(Long id) {

        // 디자인 요청 내용
        ReqDetailReqResource reqDetail = designReqRepository.getReqDetail(id);
        List<ReqInfoDetailResource> reqInfoDetail = designReqInfoRepository.getReqInfoDetail(id);

        reqInfoDetail.stream().map(this::getUrl).collect(Collectors.toList()); // s3 imgUrl로 update

        // 디자인 요청에 대한 답변 내용
        List<ReqDetailResResource> reqDetailRes = designResRepository.getReqDetailRes(id);
        List<ReqDetailResResource> reqDetailResList = reqDetailRes.stream().map(res -> {
            FileDesignRes fileDesignRes = fileDesignResRepository.findByDesignRes_IdAndRepYn(res.getId(), "Y");

            res.setFilePath(amazonS3Client.getUrl(bucket,fileDesignRes.getFilePath()).toString());
            return res;
        }).collect(Collectors.toList());

        reqDetail.setReqInfoDetailResources(reqInfoDetail);
        reqDetail.setReqDetailResResources(reqDetailResList);

        return reqDetail;
    }

    private ReqInfoDetailResource getUrl(ReqInfoDetailResource rep) {
        String imgUrl = amazonS3Client.getUrl(bucket, rep.getImgPath()).toString();
        rep.setImgPath(imgUrl);
        return rep;
    }
}

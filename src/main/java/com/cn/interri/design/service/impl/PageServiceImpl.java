package com.cn.interri.design.service.impl;

import com.amazonaws.services.s3.AmazonS3Client;
import com.cn.interri.design.domain.FileDesignReq;
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

import java.util.ArrayList;
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
    private final FileDesignReqRepository fileDesignReqRepository;

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

        // 디자인 요청 상세 정보는 여러개가 올 수 있으며, 상세 정보에서도 이미지가 여러개 올 수있다.
        reqInfoDetail.stream().map(req->{
            List<FileDesignReq> designReqInfo = fileDesignReqRepository.findByDesignReqInfo_Id(req.getInfoId()); // 디자인 요청 상세에 맞는 file을 가져온다.

            List<String> reqImgPathList = new ArrayList<>();

            for (FileDesignReq file : designReqInfo) { // file 개수만큼 반복문을 돌며 s3 이미지 저장 경로로 바꿔서 List에 넣어준다.
                reqImgPathList.add(amazonS3Client.getUrl(bucket, file.getFilePath()).toString());
            }

            req.setImgPathList(reqImgPathList); // dto의 imageList를 s3 bucket 경로로 바뀐 데이터로 업데이트 한다.
            return req;
        }).collect(Collectors.toList());


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

//    private ReqInfoDetailResource getUrl(ReqInfoDetailResource rep) {
//        String imgUrl = amazonS3Client.getUrl(bucket, rep.getImgPath()).toString();
//        rep.setImgPath(imgUrl);
//        return rep;
//    }
}

package com.cn.interri.design.request.service.impl;

import com.amazonaws.services.s3.AmazonS3Client;
import com.cn.interri.common.dto.HousingTypeDto;
import com.cn.interri.common.dto.RoomTypeDto;
import com.cn.interri.common.dto.SizeDto;
import com.cn.interri.common.dto.StyleDto;
import com.cn.interri.common.entity.CommonType;
import com.cn.interri.common.entity.CommonTypeDesign;
import com.cn.interri.common.repository.CommonTypeDesignRepository;
import com.cn.interri.common.repository.CommonTypeRepository;
import com.cn.interri.design.request.entity.DesignReq;
import com.cn.interri.design.request.entity.DesignResInfo;
import com.cn.interri.design.request.entity.FileDesignReq;
import com.cn.interri.design.request.entity.FileDesignRes;
import com.cn.interri.design.request.dto.*;
import com.cn.interri.design.request.enums.Colors;
import com.cn.interri.design.request.repository.*;
import com.cn.interri.design.request.repository.custom.DesignResRepository;
import com.cn.interri.design.request.service.PageService;
import jakarta.persistence.EntityNotFoundException;
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
    private final DesignResInfoRepository designResInfoRepository;
    private final DesignResRepository designResRepository;
    private final FileDesignResRepository fileDesignResRepository;
    private final FileDesignReqRepository fileDesignReqRepository;
    private final CommonTypeDesignRepository commonTypeDesignRepository;
    private final CommonTypeRepository commonTypeRepository;

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

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
    public ReqDetailReqResource getDesignReqDetails(Long id,String sortType) {

        // 디자인 요청 내용
        ReqDetailReqResource reqDetail = designReqRepository.getReqDetail(id);
        List<ReqInfoDetailResource> reqInfoDetail = designReqInfoRepository.getReqInfoDetail(id);

        // 디자인 요청 상세 정보는 여러 개가 올 수 있으며, 상세 정보에서도 이미지가 여러 개 올 수있다.
        reqInfoDetail.stream().map(req->{
            List<FileDesignReq> designReqInfo = fileDesignReqRepository.findByDesignReqInfo_Id(req.getInfoId()); // 디자인 요청 상세에 맞는 file을 가져온다.

            List<String> reqImgPathList = new ArrayList<>();

            for (FileDesignReq file : designReqInfo) { // file 개수만큼 반복문을 돌며 s3 이미지 저장 경로로 바꿔서 List에 넣어준다.
                reqImgPathList.add(amazonS3Client.getUrl(bucketName, file.getFilePath()).toString());
            }

            req.setImgPathList(reqImgPathList); // dto의 imageList를 s3 bucket 경로로 바뀐 데이터로 업데이트 한다.
            return req;
        }).collect(Collectors.toList());


        // 디자인 요청에 대한 답변 내용
        List<ReqDetailResResource> reqDetailRes = designResRepository.getReqDetailRes(id , sortType);

        List<ReqDetailResResource> reqDetailResList = reqDetailRes.stream().map(res -> {
            DesignResInfo designResInfo = designResInfoRepository.findTopByDesignRes_IdAndDelYn(id,"N");
            FileDesignRes fileDesignRes = fileDesignResRepository.findTopByDesignResInfo_IdAndDelYn(designResInfo.getId(),"N");

            res.setRepImgPath(amazonS3Client.getUrl(bucketName,fileDesignRes.getFilePath()).toString());
            return res;
        }).collect(Collectors.toList());

        reqDetail.setReqInfoDetailResources(reqInfoDetail);
        reqDetail.setReqDetailResResources(reqDetailResList);

        return reqDetail;
    }

    @Override
    public List<String> getResRoomTypeNm(Long id) {

        List<String> roomTypeNmList = new ArrayList<>();

        DesignReq designReq = designReqRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        designReq.getDesignReqInfoList().stream().map(info -> {
            // design_req_info_id에 맞는 common_type_design 정보를 가져온다 (필요한 데이터 : common_type_id)
            CommonTypeDesign common = commonTypeDesignRepository.findByDesignReqInfo_Id(info.getId());

            // 위에서 구한 common_type_design 정보로 common_type_id를 구하고 이 id로 common_type 테이블에서 id에 맞는 common_type_nm을 가져온다.
            CommonType commonType = commonTypeRepository.findById(common.getCommonType().getId()).orElseThrow(EntityNotFoundException::new);
            roomTypeNmList.add(commonType.getName());
            return info;
        }).collect(Collectors.toList());

        return roomTypeNmList;

    }


}

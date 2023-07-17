package com.cn.interri.design.request.controller;

import com.cn.interri.common.dto.ResponseDto;
import com.cn.interri.design.request.dto.RegistReqDto;
import com.cn.interri.design.request.dto.ReqDetailReqResource;
import com.cn.interri.design.request.dto.ReqRegistrationResource;
import com.cn.interri.design.request.dto.ResRegistrationParam;
import com.cn.interri.design.request.service.PageService;
import com.cn.interri.design.request.service.RegisterDesignService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/design")
public class DesignController {

    private final PageService pageService;
    private final RegisterDesignService registerDesignService;

    @GetMapping(value = "/req", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto<ReqRegistrationResource>> getRegistrationPage() {
        ReqRegistrationResource resource = pageService.getRegistrationPageResource();

        return ResponseEntity.ok()
                .body(ResponseDto.<ReqRegistrationResource>builder()
                        .data(resource)
                        .build());
    }

    @PostMapping(value = "/req")
    public ResponseEntity<ResponseDto<String>> registerDesignRequest(RegistReqDto designRequest) throws Exception {
        registerDesignService.saveDesignRequest(designRequest);
        return ResponseEntity.ok()
                .body(ResponseDto.<String>builder()
//                        .message("등록 성공")
                        .build());
    }

    @GetMapping("/req/{id}/details")
    public ResponseEntity<ResponseDto<ReqDetailReqResource>> getDesignReqDetails(
            @PathVariable("id") Long id,
            @RequestParam(value = "sortType" , required = false) String sortType
    ) throws Exception {
        ReqDetailReqResource resource = pageService.getDesignReqDetails(id,sortType);
        return ResponseEntity.ok()
                .body(ResponseDto.<ReqDetailReqResource>builder()
                        .data(resource)
                        .build());
    }

    /* 디자인 응답 등록 페이지 로드 시 [공간] 데이터 조회 */
    @GetMapping("/res/{id}/room")
    public ResponseEntity<ResponseDto<List<String>>> getResRegistrationPage(
            @PathVariable("id") long id
    ){
        List<String> resource = pageService.getResRoomTypeNm(id);
        return ResponseEntity.ok()
                .body(ResponseDto.<List<String>>builder()
                        .data(resource)
                        .build());
    }

    /**
     * formData postman test 방법
     * @See https://velog.io/@myway00/postman-list-DTO%EB%A5%BC-form-data%EB%A1%9C-%EB%B3%B4%EB%82%B4%EA%B8%B0
     */
    @PostMapping("/res/{id}")
    public ResponseEntity<ResponseDto<String>> registerDesignResponse(
            @PathVariable("id") long id,
            ResRegistrationParam res
    ) throws Exception {
        registerDesignService.saveDesignResponse(id, res);
        return null;
    }


}

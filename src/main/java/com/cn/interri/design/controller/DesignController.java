package com.cn.interri.design.controller;

import com.cn.interri.common.dto.ResponseDto;
import com.cn.interri.design.dto.ReqDetailReqResource;
import com.cn.interri.design.dto.ReqRegistrationResource;
import com.cn.interri.design.service.PageService;
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

    @GetMapping(value = "/req", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto<ReqRegistrationResource>> getRegistrationPage() {
        ReqRegistrationResource resource = pageService.getRegistrationPageResource();

        return ResponseEntity.ok()
                .body(ResponseDto.<ReqRegistrationResource>builder()
                        .data(resource)
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
    @GetMapping("/res/{id}")
    public ResponseEntity<ResponseDto<List<String>>> getResRegistrationPage(@PathVariable("id") Long id){
        List<String> resource = pageService.getResRoomTypeNm(id);
        return ResponseEntity.ok()
                .body(ResponseDto.<List<String>>builder()
                        .data(resource)
                        .build());
    }


}

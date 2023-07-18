package com.cn.interri.design.inquiry.controller;

import com.cn.interri.common.dto.ResponseDto;
import com.cn.interri.design.inquiry.dto.RegistReqDto;
import com.cn.interri.design.inquiry.dto.ReqDetailReqResource;
import com.cn.interri.design.inquiry.dto.ReqRegistrationResource;
import com.cn.interri.design.reply.dto.ResRegistrationParam;
import com.cn.interri.design.inquiry.service.PageService;
import com.cn.interri.design.inquiry.service.RegisterDesignService;
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
}

package com.cn.interri.design.controller;

import com.cn.interri.common.dto.ResponseDto;
import com.cn.interri.design.dto.ReqDetailReqResource;
import com.cn.interri.design.dto.ReqRegistrationResource;
import com.cn.interri.design.service.PageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/design")
public class DesignController {

    private final PageService pageService;

    @GetMapping("/req")
    public ResponseEntity<ResponseDto<ReqRegistrationResource>> getRegistrationPage() {
        ReqRegistrationResource resource = pageService.getRegistrationPageResource();

        return ResponseEntity.ok()
                .body(ResponseDto.<ReqRegistrationResource>builder()
                        .data(resource)
                        .build());
    }

    @GetMapping("/req/{id}/details")
    public ResponseEntity<ResponseDto<ReqDetailReqResource>> getDesignReqDetails(@PathVariable("id") Long id) throws Exception {
        ReqDetailReqResource resource = pageService.getDesignReqDetails(id);
        return ResponseEntity.ok()
                .body(ResponseDto.<ReqDetailReqResource>builder()
                        .data(resource)
                        .build());
    }


}

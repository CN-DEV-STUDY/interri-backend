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

    @GetMapping("/design/{id}/details")
    public ResponseEntity<ResponseDto<ReqDetailReqResource>> getDesignReqDetails(@PathVariable("id") Long id) throws Exception {
        ReqDetailReqResource resource = pageService.getDesignReqDetails(id);
        return ResponseEntity.ok()
                .body(ResponseDto.<ReqDetailReqResource>builder()
                        .data(resource)
                        .build());
    }

//    @PostMapping(value = "/req", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public Response

}

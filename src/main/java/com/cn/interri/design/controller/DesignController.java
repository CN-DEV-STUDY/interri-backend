package com.cn.interri.design.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/design")
public class DesignController {

    @GetMapping("/design/{id}/details")
    public void getDesignReqDetails() throws Exception {

    }
}

package com.cn.interri.index.controller;

import com.cn.interri.common.dto.ResponseDto;
import com.cn.interri.index.dto.InteriorTrendsResponse;
import com.cn.interri.index.service.TrendsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequestMapping("/index")
@RequiredArgsConstructor
@RestController
public class IndexController {

    private final TrendsService trendsService;

    /**
     * TODO: batch에서 일주일 간의 데이터 연산해서 레디스에 저장한 데이터를 fetch하는 걸로 변경 필요
     *
     * @return
     */
    @GetMapping("/trends")
    public ResponseEntity<ResponseDto<Object>> trends() {
        List<InteriorTrendsResponse> responses = trendsService.getTrends();
        return ResponseEntity.ok(ResponseDto.builder()
                        .data(responses)
                .build());
    }


}

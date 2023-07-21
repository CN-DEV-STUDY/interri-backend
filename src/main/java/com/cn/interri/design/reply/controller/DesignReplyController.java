package com.cn.interri.design.reply.controller;

import com.cn.interri.common.dto.ResponseDto;

import com.cn.interri.design.reply.dto.ReplyRegistParam;
import com.cn.interri.design.reply.service.DesignRegisterReplyService;
import com.cn.interri.design.reply.service.ReplyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/design/reply")
@RestController
@RequiredArgsConstructor
public class DesignReplyController {
    private final ReplyPageService pageService;
    private final DesignRegisterReplyService registerReplyService;


    /* 디자인 응답 등록 페이지 로드 시 [공간] 데이터 조회 */
    @GetMapping("/{id}/room")
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
    @PostMapping("/regist/{id}")
    public ResponseEntity<ResponseDto<String>> registerDesignResponse(
            @PathVariable("id") long id, // 디자인 요청 id
            ReplyRegistParam res
    ) throws Exception {
        registerReplyService.saveDesignReply(id, res);
        return null;
    }
}

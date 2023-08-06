package com.cn.interri.batch.controller;

import com.cn.interri.user.entity.User.User;
import com.cn.interri.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;
import java.util.List;
import java.util.TimeZone;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BatchControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    @Test
    void 인테리어트렌드_주간랭킹_배치_실행_shoudlSuccess() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/batch/weekly-ranking"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void localDateTimeTest() {
        System.out.println(LocalDateTime.now());

        // 기존의 글로벌 타임존 출력
       System.out.println("Default Time Zone: " + TimeZone.getDefault().getID());

       // 새로운 글로벌 타임존으로 변경
       TimeZone.setDefault(TimeZone.getTimeZone("America/New_York"));

       // 변경된 글로벌 타임존 출력
       System.out.println("New Default Time Zone: " + TimeZone.getDefault().getID());
        System.out.println(LocalDateTime.now());


        List<User> byRegDateBetween = userRepository.findByRegDateBetween(LocalDateTime.now().minusWeeks(3), LocalDateTime.now());
        for (User user : byRegDateBetween) {
            System.out.println("user.getEmail() = " + user.getEmail());
        }
    }
}
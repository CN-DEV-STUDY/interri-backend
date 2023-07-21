package com.cn.interri.user.controller;

import com.cn.interri.user.dto.UserLoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class LoginControllerTest {

    @Value(value="${server.port}")
    private int port;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;
    
    private UserLoginRequest createUser() {
        UserLoginRequest request = new UserLoginRequest();
        request.setEmail("admin");
        request.setPassword("1234");
        return request;
    }

    @DisplayName("로그인 테스트 - 없는 사용자")
    @Test
    void login() throws Exception {
        // given
        UserLoginRequest request = createUser();

        // when
        String body = objectMapper.writeValueAsString(request);

        // then
        assertThrows(BadCredentialsException.class,
                () -> mockMvc.perform(post("/login")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()));

        mockMvc.perform(post("/login")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnauthorized());

//                .content(body)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo();


    }

//    @Test
//    public void whenUserAccessWithWrongCredentialsWithDelegatedEntryPoint_shouldFail() throws Exception {
////        RestError re = new RestError(HttpStatus.UNAUTHORIZED.toString(), "Authentication failed");
//        mockMvc.perform(formLogin("/login").user("username", "admin")
//                        .password("password", "wrong")
//                        .acceptMediaType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isUnauthorized())
//                .andExpect(jsonPath("$.errorMessage", is(re.getErrorMessage())));
//    }
}
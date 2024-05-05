package com.example.TravelPlanner;

import com.example.TravelPlanner.dto.login.LoginDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginSecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void loginTest_Success() throws Exception {
        // 유효한 로그인 정보를 포함하는 DTO 생성
        LoginDto loginDto = new LoginDto();
        loginDto.setMemberId("jaeah2171");
        loginDto.setPassword("$2a$10$ydh.eNcGojiiHFf19H.XKOMnVaHj3R1i2hbLoecJ.89gCxzz0SymC");

        // 로그인 요청을 POST 메서드로 보냄
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDto)))
                // 예상되는 응답 상태 코드는 OK(200)
                .andExpect(MockMvcResultMatchers.status().isOk())
                // 예상되는 응답 본문은 "Login successful"
                .andExpect(MockMvcResultMatchers.content().string("Login successful"));
    }

    @Test
    public void loginTest_Failure() throws Exception {
        // 잘못된 로그인 정보를 포함하는 DTO 생성
        LoginDto loginDto = new LoginDto();
        loginDto.setMemberId("jaeah2171");
        loginDto.setPassword("4321");

        // 로그인 요청을 POST 메서드로 보냄
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDto)))
                // 예상되는 응답 상태 코드는 UNAUTHORIZED(401)
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                // 예상되는 응답 본문은 "Login failed"
                .andExpect(MockMvcResultMatchers.content().string("Login failed"));
    }
}

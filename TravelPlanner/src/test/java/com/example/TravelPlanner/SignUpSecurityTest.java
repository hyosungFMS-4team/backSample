package com.example.TravelPlanner;

import com.example.TravelPlanner.dto.signup.SignUpDto;
import com.example.TravelPlanner.dto.signup.ValidateMemberIdDto;
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
public class SignUpSecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void signUpTest_Success() throws Exception {
        // 유효한 회원가입 정보를 포함하는 DTO 생성
        SignUpDto signUpDto = new SignUpDto();
        signUpDto.setMemberId("abcd");
        signUpDto.setPassword("6687");
        signUpDto.setEmail("test@example.com");
        signUpDto.setName("TestUser");
        signUpDto.setPhone("010-9180-6687");
        signUpDto.setAge(35);
        signUpDto.setGender("여");

        // 회원가입 요청을 POST 메서드로 보냄
        mockMvc.perform(MockMvcRequestBuilders.post("/signup/member")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signUpDto)))
                // 예상되는 응답 상태 코드는 OK(200)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void signUpTest_Failure() throws Exception {
        // 이미 존재하는 아이디를 포함하는 DTO 생성
        ValidateMemberIdDto validateMemberIdDto = new ValidateMemberIdDto();
        validateMemberIdDto.setMemberId("jaeah2171");

        // 아이디 중복 체크 요청을 POST 메서드로 보냄
        mockMvc.perform(MockMvcRequestBuilders.post("/signup/memberid-check")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validateMemberIdDto)))
                // 예상되는 응답 상태 코드는 OK(200)
                .andExpect(MockMvcResultMatchers.status().isOk())
                // 응답 본문에서 아이디가 이미 존재함을 나타내는 필드를 확인
                .andExpect(MockMvcResultMatchers.jsonPath("$.exists").value(true));

        // 중복된 아이디로 회원가입 요청을 보내면 실패해야 함
        SignUpDto signUpDto = new SignUpDto();
        signUpDto.setMemberId("jaeah2171"); // 이미 존재하는 아이디 사용
        signUpDto.setPassword("6687");
        signUpDto.setEmail("test@example.com");
        signUpDto.setName("TestUser");
        signUpDto.setPhone("010-9180-6687");
        signUpDto.setAge(35);
        signUpDto.setGender("여");

        // 회원가입 요청을 POST 메서드로 보냄
        mockMvc.perform(MockMvcRequestBuilders.post("/signup/member")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signUpDto)))
                // 예상되는 응답 상태 코드는 BAD_REQUEST(400)
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}

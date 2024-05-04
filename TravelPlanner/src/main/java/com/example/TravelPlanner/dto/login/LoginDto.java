package com.example.TravelPlanner.dto.login;

import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto { //로그인 데이터 전송
    @NotNull
    private String memberId;
    private String password;
}

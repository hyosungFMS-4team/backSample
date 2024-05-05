package com.example.TravelPlanner.dto.User;

import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    private String memberId;
    private String password;
    private String email;
    private String phone;
    private String name;
    private String gender;
    private int age;
}

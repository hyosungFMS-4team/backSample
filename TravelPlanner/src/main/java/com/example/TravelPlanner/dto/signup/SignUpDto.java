package com.example.TravelPlanner.dto.signup;

import jakarta.persistence.Column;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {

        private String memberId;
        private String password;
        private String email;
        private String phone;
        private String name;
        private String gender;
        private int age;

}

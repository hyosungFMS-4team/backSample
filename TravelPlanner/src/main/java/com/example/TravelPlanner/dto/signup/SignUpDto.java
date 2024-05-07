package com.example.TravelPlanner.dto.signup;

import lombok.*;

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

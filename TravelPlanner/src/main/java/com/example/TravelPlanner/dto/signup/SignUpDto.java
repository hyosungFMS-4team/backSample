package com.example.TravelPlanner.dto.signup;

import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {

        private Long id;
        private String email;
        private String name;
        private String userid;
        private String password;

}

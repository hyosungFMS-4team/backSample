package com.example.TravelPlanner.dto.signup;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ValidateUseridDto {
    private String userid;
    private boolean exists;
}

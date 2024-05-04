package com.example.TravelPlanner.dto.signup;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ValidateMemberIdDto {
    private String memberId;
    private boolean exists;
}

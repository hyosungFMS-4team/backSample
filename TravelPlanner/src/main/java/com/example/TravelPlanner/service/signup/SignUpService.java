package com.example.TravelPlanner.service.signup;

import com.example.TravelPlanner.dto.signup.SignUpDto;
import com.example.TravelPlanner.dto.signup.ValidateMemberIdDto;

public interface SignUpService {

    SignUpDto join(SignUpDto signUpDto);

    ValidateMemberIdDto checkMemberId(ValidateMemberIdDto validateMemberIdDto);
}

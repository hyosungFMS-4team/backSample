package com.example.TravelPlanner.service.signup;

import com.example.TravelPlanner.dto.signup.SignUpDto;
import com.example.TravelPlanner.dto.signup.ValidateMemberIdDto;

public interface SignUpService {

    SignUpDto signup(SignUpDto signUpDto);
    //ValidateMemberIdDto checkMemberId(ValidateMemberIdDto validateMemberIdDto);
    boolean checkMemberId(String memberId);
}

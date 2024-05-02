package com.example.TravelPlanner.service.signup;

import com.example.TravelPlanner.dto.signup.SignUpDto;
import com.example.TravelPlanner.dto.signup.ValidateUseridDto;

public interface SignUpService {

    SignUpDto join(SignUpDto signUpDto);

    ValidateUseridDto checkUserid(ValidateUseridDto validateUseridDto);
}

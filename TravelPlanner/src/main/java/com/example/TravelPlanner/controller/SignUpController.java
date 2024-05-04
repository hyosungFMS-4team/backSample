package com.example.TravelPlanner.controller;


import com.example.TravelPlanner.dto.signup.SignUpDto;
import com.example.TravelPlanner.dto.signup.ValidateMemberIdDto;
import com.example.TravelPlanner.service.signup.SignUpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//구현완 : DB 삽입되면 확인만 하면 될듯  (5/1)
@RestController
@RequestMapping("/signup")
//회원가입 정보를 받을 Controller
public class SignUpController {

    @Autowired
    private final SignUpServiceImpl signUpService;

    public SignUpController(SignUpServiceImpl signUpService) {
        this.signUpService = signUpService;
    }

    @PostMapping("/user")
    public ResponseEntity<SignUpDto> signUp(@RequestBody SignUpDto signUpDto) {
        SignUpDto registeredUser = signUpService.join(signUpDto);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/userid-check")
    public ValidateMemberIdDto checkUserid(@RequestBody ValidateMemberIdDto validateMemberIdDto){
        return signUpService.checkMemberId(validateMemberIdDto);
    }

}


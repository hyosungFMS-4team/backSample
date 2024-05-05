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

    //나왜 여기 /member 했지 --> 생각 안나면 없애자(security도 고쳐야됌)
//    @PostMapping("/member")
//    public ResponseEntity<SignUpDto> signUp(@RequestBody SignUpDto signUpDto) {
//        SignUpDto registeredUser = signUpService.signup(signUpDto);
//        return ResponseEntity.ok(registeredUser);
//    }

    @PostMapping("/member")
    public ResponseEntity<?> signUp(@RequestBody SignUpDto signUpDto) {
        SignUpDto registeredUser = signUpService.signup(signUpDto);
        if (registeredUser != null) {
            return ResponseEntity.ok(registeredUser);
        } else {
            return ResponseEntity.badRequest().body("중복된 회원 아이디입니다.");
        }
    }



    //유저 아이디 중복체크
    @PostMapping("/memberid-check")
    public ValidateMemberIdDto checkUserid(@RequestBody ValidateMemberIdDto validateMemberIdDto){
        return signUpService.checkMemberId(validateMemberIdDto);
    }

}


package com.example.TravelPlanner.controller;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RestController;

//회원가입 Form
@RestController
@Getter
public class SignUpFormController {
    private String name;
    private String userid;
    private String email;
    private String password;
}

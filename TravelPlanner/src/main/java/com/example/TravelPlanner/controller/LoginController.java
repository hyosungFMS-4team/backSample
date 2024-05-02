package com.example.TravelPlanner.controller;


import com.example.TravelPlanner.domain.User;
import com.example.TravelPlanner.dto.login.LoginDto;
import com.example.TravelPlanner.service.login.LoginServiceImpl;
import com.example.TravelPlanner.service.login.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//구현 완 : DB 삽입되면 수정해서 확이만 하면 될 듯(4/30)
@RestController
@RequestMapping(value = "/login")
@RequiredArgsConstructor //해당 필드로 구성된 생성자를 @RequiredArgsConstructor가 자동으로 생성자 주입에 대한 코드를 생성
public class LoginController {

    private final LoginServiceImpl loginService;
    private final UserServiceImpl userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        String userId = loginDto.getUserid();
        String password = loginDto.getPassword();

        if (loginService.validateUser(userId, password)) {
            //System.out.println("true : " + userId + password);
            return ResponseEntity.ok().body("Login successful");
        } else {
            //System.out.println("false : " + userId + password);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
        }
    }

    //나중에 삭제해야함
    @GetMapping("/users")
    public List<User> getAllUsers() {
        userService.printAllUsersToConsole();

        return userService.getAllUsers();
    }
}

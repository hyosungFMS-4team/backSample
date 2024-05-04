package com.example.TravelPlanner.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class SignUp {

    @Id
    @Column(name="member_id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String memberId;

    @Column(name="password")
    private String password;

    @Column(name="email")
    private String email;

    @Column(name="phone")
    private String phone;

    @Column(name="name")
    private String name;

    @Column(name="gender")
    private String gender;

    @Column(name="age")
    private int age;
}

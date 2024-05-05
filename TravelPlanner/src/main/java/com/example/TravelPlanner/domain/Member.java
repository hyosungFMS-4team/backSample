package com.example.TravelPlanner.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//CREATE TABLE member (--로그인 할 수 있는 member 테이블
//        member_id   VARCHAR2(50)    PRIMARY KEY,
//        password    VARCHAR2(20)    NOT NULL,
//        email       VARCHAR2(200)   NOT NULL,
//        phone       VARCHAR2(20)    NOT NULL,
//        name        VARCHAR2(20)    NOT NULL,
//        gender      VARCHAR2(20)    NOT NULL,
//        age         INT             NOT NULL
//);

@Entity
@Table(name = "member")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @Column(name="member_id")
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

package com.example.TravelPlanner.domain;

import jakarta.persistence.*;
import lombok.*;

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
//클래스의 특정 멤버나 메소드가 같은 패키지 내의 다른 클래스나 해당 클래스를 상속한 클래스에서만 접근 가능하고, 외부 패키지의 클래스에서는 직접적인 접근이 제한
@NoArgsConstructor//(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member {

    @Id
    @Column(name="member_id", unique = true)
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

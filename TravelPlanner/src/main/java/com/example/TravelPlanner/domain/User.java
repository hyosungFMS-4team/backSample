package com.example.TravelPlanner.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//CREATE TABLE users (
//     id NUMBER PRIMARY KEY,
//     name VARCHAR2(255) NOT NULL,
//     userid VARCHAR2(255) NOT NULL,
//     email VARCHAR2(255) NOT NULL,
//     password VARCHAR2(255) NOT NULL
//);

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Column(name="id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Id
    @Column(name="userid")
    private String userid;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;


}

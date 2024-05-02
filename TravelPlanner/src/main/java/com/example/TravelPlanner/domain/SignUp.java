package com.example.TravelPlanner.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class SignUp {

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

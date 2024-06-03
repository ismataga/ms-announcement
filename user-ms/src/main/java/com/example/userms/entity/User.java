package com.example.userms.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Entity
@Slf4j
@Data
@Component
@SuperBuilder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String mobileNumber;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private boolean isLogin;
    private UUID uuid;
    @Column(nullable = false)
    private boolean emailConfirmed = false;

//    @ToString.Exclude
//    @JoinColumn(name = "email_verify_code_id")
//    @OneToOne(cascade = CascadeType.PERSIST)
//    private EmailVerifyCode emailVerifyCode;
//    private int attemptCount;
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "user_authority",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "authority_id"))
//    private Set<Authority> authorities;


}

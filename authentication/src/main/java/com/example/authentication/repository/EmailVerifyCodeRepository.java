package com.example.authentication.repository;


import com.example.authentication.entity.EmailVerifyCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailVerifyCodeRepository extends JpaRepository<EmailVerifyCode, Long> {

  Optional<EmailVerifyCode> findByVerificationCode(String code);
}

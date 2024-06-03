package com.example.authentication.mapper;


import com.example.authentication.entity.EmailVerifyCode;

public enum EmailVerifyCodeMapper {
  EMAIL_VERIFY_CODE_MAPPER;


  public EmailVerifyCode mapToEntity(String verificationCode) {
    return EmailVerifyCode.builder()
        .verificationCode(verificationCode)
        .build();
  }
}

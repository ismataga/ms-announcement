package com.example.authentication.mapper;

import com.example.authentication.client.request.UserInfoClientRequest;
import com.example.authentication.entity.EmailVerifyCode;
import com.example.authentication.entity.User;
import com.example.authentication.model.request.UserInfoRequest;

public enum UserMapper {
  USER_MAPPER;

  public User mapToEntity(String email, EmailVerifyCode emailVerifyCode) {
    return User.builder()
        .email(email)
        .emailVerifyCode(emailVerifyCode)
        .build();
  }

  public UserInfoClientRequest mapToClient(UserInfoRequest signupRequest) {
    return UserInfoClientRequest.builder()
        .fullName(signupRequest.fullName())
        .username(signupRequest.username())
        .build();
  }
}

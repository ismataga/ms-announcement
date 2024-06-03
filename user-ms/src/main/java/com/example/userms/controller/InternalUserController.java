package com.example.userms.controller;


import com.example.userms.model.request.UserInfoClientRequest;
import com.example.userms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("internal/v1/users")
public class InternalUserController {

  private final UserService userService;

  @PostMapping
  public void createUser(@RequestBody @Validated UserInfoClientRequest userInfoClientRequest) {
    userService.createUser(userInfoClientRequest);
  }
}

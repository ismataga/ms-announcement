package com.example.authentication.client;


import com.example.authentication.client.request.UserInfoClientRequest;
import com.example.authentication.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import static com.example.authentication.model.constants.Headers.USER_ID;


@FeignClient(name = "user-service", configuration = FeignConfig.class)
public interface UserClient {

  @PostMapping(path = "internal/v1/users")
  void createUser(@RequestBody @Validated UserInfoClientRequest userInfoClientRequest);


}

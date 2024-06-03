package com.example.authentication.controller;

import com.example.authentication.model.request.SignInRequest;
import com.example.authentication.model.request.UserInfoRequest;
import com.example.authentication.model.response.TokenResponse;
import com.example.authentication.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static com.example.authentication.model.constants.Headers.USER_ID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class AuthController {

  private final AuthService authService;

  @PostMapping("/user-info")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void fillUserInfo(@RequestBody @Validated UserInfoRequest userInfoRequest){
    authService.fillUserInfo(userInfoRequest);
  }

  @PostMapping("/log-in")
  @ResponseStatus(HttpStatus.OK)
  public TokenResponse signIn(@RequestBody @Validated SignInRequest signInRequest) {
    return authService.signIn(signInRequest);
  }

  @PostMapping("/log-in/google")
  @ResponseStatus(HttpStatus.OK)
  public TokenResponse signInWithGoogle(@RequestParam String token) {
    return authService.signInWithGoogle(token);
  }

  @PostMapping("/log-in/facebook")
  @ResponseStatus(HttpStatus.OK)
  public TokenResponse signInWithFacebook(@RequestParam String token) {
    return authService.signInWithFacebook(token);
  }

  @PostMapping("/log-in/x")
  @ResponseStatus(HttpStatus.OK)
  public TokenResponse signInWithX(@RequestParam String token) {
    return authService.signInWithX(token);
  }

  @PostMapping("/log-in/linkedin")
  @ResponseStatus(HttpStatus.OK)
  public TokenResponse signInWithLinkedin(@RequestParam String token) {
    return authService.signInWithLinkedin(token);
  }
}

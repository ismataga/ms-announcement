package com.example.authentication.controller;


import com.example.authentication.model.constants.Headers;
import com.example.authentication.model.response.TokenResponse;
import com.example.authentication.model.response.ValidateTokenResponse;
import com.example.authentication.service.TokenService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.authentication.model.constants.Headers;



@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/token")
public class TokenController {

  private final TokenService tokenService;


  @PostMapping("/refresh")
  public TokenResponse refreshTokens(@RequestHeader(Headers.REFRESH_TOKEN) @NotBlank String refreshToken) {
    return tokenService.refreshTokens(refreshToken);
  }

  @PostMapping("/validate")
  public ValidateTokenResponse validateToken(@RequestHeader(Headers.AUTHORIZATION) @NotBlank
                                               String accessToken) {
    return tokenService.validateToken(accessToken);
  }

}

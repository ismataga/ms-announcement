package com.example.authentication.controller;


import com.example.authentication.model.request.EmailRequest;
import com.example.authentication.model.response.TokenResponse;
import com.example.authentication.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/email")
public class EmailController {

  private final EmailService emailService;

  @PostMapping("/send")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void sendEmail(@RequestBody @Validated EmailRequest emailRequest) {
    emailService.sendEmail(emailRequest);
  }

  @PostMapping("/verify")
  @ResponseStatus(HttpStatus.OK)
  public TokenResponse verifyEmail(String code) {
    return emailService.verifyEmail(code);
  }
}

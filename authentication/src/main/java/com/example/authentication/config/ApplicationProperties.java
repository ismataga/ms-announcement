package com.example.authentication.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class ApplicationProperties {

  @Value("${app.email-confirmation.url}")
  private String emailConfirmationUrl;

  @Value("${app.email-confirmation.redirect-url-after-confirmation}")
  private String redirectUrlAfterEmailConfirm;

  @Value("${jwt.refreshToken.expiration.count}")
  private Integer refTokenExpCount;

  @Value("${jwt.accessToken.expiration.time}")
  private Integer accessTokenExpTime;

  @Value("${jwt.refreshToken.expiration.time}")
  private Integer refTokenExpTime;  // 15 DAYS

}

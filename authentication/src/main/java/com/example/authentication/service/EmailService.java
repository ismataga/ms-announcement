package com.example.authentication.service;

import com.example.authentication.config.ApplicationProperties;
import com.example.authentication.exception.AppException;
import com.example.authentication.model.request.EmailBodyRequest;
import com.example.authentication.model.request.EmailRequest;
import com.example.authentication.model.response.TokenResponse;
import com.example.authentication.repository.EmailVerifyCodeRepository;
import com.example.authentication.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import static com.example.authentication.mapper.EmailVerifyCodeMapper.EMAIL_VERIFY_CODE_MAPPER;
import static com.example.authentication.mapper.UserMapper.USER_MAPPER;
import static com.example.authentication.model.constants.ExceptionConstants.DUPLICATE_EMAIL_EXCEPTION;
import static com.example.authentication.model.constants.ExceptionConstants.EMAIL_CODE_EXPIRED_EXCEPTION;
import static com.example.authentication.model.constants.ExceptionConstants.NOT_FOUND_EXCEPTION;
import static com.example.authentication.model.constants.ExceptionConstants.UNEXPECTED_EXCEPTION;
import static com.example.authentication.model.constants.Headers.USER_ID;


@Log4j2
@Service
@RequiredArgsConstructor
public class EmailService {

  private final TokenService tokenService;
  private final UserRepository userRepository;
  private final EmailSenderService emailSenderService;
  private final HttpServletResponse httpServletResponse;
  private final ApplicationProperties applicationProperties;
  private final EmailVerifyCodeRepository emailVerifyCodeRepository;


  public void sendEmail(EmailRequest emailRequest) {
    var email = emailRequest.email();
    var verificationCode = generateVerificationCode();

    var emailBodyRequest = EmailBodyRequest.builder()
        .receiverEmail(email)
        .verificationCode(verificationCode)
        .build();

    var userByEmail = userRepository.findByEmail(email);

    if (userByEmail.isPresent()) {
      var user = userByEmail.get();
      if (!user.isEmailConfirmed()) {
        emailSenderService.send(emailBodyRequest);
        return;
      } else {
        log.error("sendEmail.error: Email already exists");
        throw new AppException(email, DUPLICATE_EMAIL_EXCEPTION);
      }
    }

    var emailVerifyCode = EMAIL_VERIFY_CODE_MAPPER.mapToEntity(verificationCode);
    emailVerifyCode.setExpiredAt(Instant.now().plus(5, ChronoUnit.MINUTES));

    var user = USER_MAPPER.mapToEntity(email, emailVerifyCode);
    user.setEmailConfirmed(false);

    userRepository.save(user);
    emailSenderService.send(emailBodyRequest);
  }

  public TokenResponse verifyEmail(String code) {
    var emailVerifyCode = emailVerifyCodeRepository.findByVerificationCode(code).orElseThrow(() ->
        new AppException("email verify code not found", NOT_FOUND_EXCEPTION));

    if (emailVerifyCode.getExpiredAt().isBefore(Instant.now())) {
      throw new AppException(EMAIL_CODE_EXPIRED_EXCEPTION);
    }

    var user = emailVerifyCode.getUser();

    emailVerifyCode.setExpiredAt(Instant.now());
    user.setEmailConfirmed(true);

    emailVerifyCodeRepository.save(emailVerifyCode);
    userRepository.save(user);

    try {
      httpServletResponse.setHeader(USER_ID, String.valueOf(user.getId()));
      httpServletResponse.sendRedirect(applicationProperties.getRedirectUrlAfterEmailConfirm());
    } catch (IOException exception) {
      log.error("verifyEmail.ERROR: ", exception);
      throw new AppException(UNEXPECTED_EXCEPTION);
    }

    return tokenService.generateToken(user.getId(), applicationProperties.getRefTokenExpCount());
  }

  private String generateVerificationCode() {
    return UUID.randomUUID().toString().replace("-", "");
  }

}

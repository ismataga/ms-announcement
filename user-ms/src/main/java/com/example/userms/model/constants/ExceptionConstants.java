package com.example.userms.model.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@RequiredArgsConstructor
public enum ExceptionConstants {

  UNEXPECTED_EXCEPTION("unexpected exception", INTERNAL_SERVER_ERROR),
  METHOD_NOT_ALLOWED_EXCEPTION("method not allowed exception", METHOD_NOT_ALLOWED),
  METHOD_ARGUMENT_NOT_VALID("method argument not valid", BAD_REQUEST),
  NOT_FOUND_EXCEPTION("Not found", NOT_FOUND),
  DUPLICATE_EMAIL_EXCEPTION("duplicate email exception ", BAD_REQUEST),
  EMAIL_CODE_EXPIRED_EXCEPTION("email confirmation code already expired", BAD_REQUEST),
  TOKEN_EXPIRED("Token expired", UNAUTHORIZED),
  USER_UNAUTHORIZED("User unauthorized", UNAUTHORIZED),
  REFRESH_TOKEN_EXPIRED("Refresh token expired", UNAUTHORIZED),
  REFRESH_TOKEN_COUNT_EXPIRED("Refresh token count expired", UNAUTHORIZED),
  BAD_REQUEST_EXCEPTION("Bad request", BAD_REQUEST),
  EMAIL_SEND_EXCEPTION("Cannot send email", INTERNAL_SERVER_ERROR),
  USERNAME_EXIST_EXCEPTION("Username already exist ", BAD_REQUEST);


  private final String userMessage;
  private final HttpStatus httpStatus;
}
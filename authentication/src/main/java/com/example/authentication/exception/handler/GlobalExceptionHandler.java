package com.example.authentication.exception.handler;

import com.example.authentication.exception.AppException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.MethodNotAllowedException;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.authentication.model.constants.ExceptionConstants.METHOD_NOT_ALLOWED_EXCEPTION;
import static com.example.authentication.model.constants.ExceptionConstants.UNEXPECTED_EXCEPTION;
import static org.springframework.http.HttpStatus.*;

@Log4j2
@RestControllerAdvice
public class
GlobalExceptionHandler {

  @ExceptionHandler(Exception.class)
  @ResponseStatus(INTERNAL_SERVER_ERROR)
  public ExceptionResponse handle(Exception ex) {
    log.error("Exception: ", ex);
    return new ExceptionResponse(UNEXPECTED_EXCEPTION, ex.getMessage());
  }

  @ExceptionHandler(AppException.class)
  public ResponseEntity<ExceptionResponse> handle(AppException ex) {
    log.error("ResourceNotFoundException: ", ex);

    ExceptionResponse exceptionResponse = ExceptionResponse.builder()
        .errorMessage(ex.getMessage())
        .userMessage(ex.getUserMessage())
        .build();

    return ResponseEntity.status(ex.getHttpStatus()).body(exceptionResponse);
  }

  @ExceptionHandler(MethodNotAllowedException.class)
  @ResponseStatus(METHOD_NOT_ALLOWED)
  public ExceptionResponse handle(MethodNotAllowedException ex) {
    log.error("MethodNotAllowedException: ", ex);
    return new ExceptionResponse(METHOD_NOT_ALLOWED_EXCEPTION, ex.getMessage());
  }

  @ResponseStatus(BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ExceptionResponse handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
    log.error("MethodArgumentNotValidException: ", ex);
    List<ValidationErrorResponse> errorsForBadRequest = getErrorsForBadRequest(ex);
    return ExceptionResponse.builder()
        .userMessage("Method arguments not valid")
        .errorMessage(ex.getMessage())
        .validationErrors(errorsForBadRequest)
        .build();
  }

  private List<ValidationErrorResponse> getErrorsForBadRequest(MethodArgumentNotValidException ex) {
    return ex.getBindingResult().getFieldErrors().stream()
        .map(error -> ValidationErrorResponse.builder()
            .field(error.getField())
            .message(error.getDefaultMessage())
            .build())
        .collect(Collectors.toList());
  }
}

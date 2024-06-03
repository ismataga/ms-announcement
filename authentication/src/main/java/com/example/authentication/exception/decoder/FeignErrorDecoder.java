package com.example.authentication.exception.decoder;

import com.example.authentication.exception.AppException;
import com.example.authentication.exception.handler.ExceptionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import feign.codec.ErrorDecoder;

import static com.example.authentication.model.constants.ExceptionConstants.BAD_REQUEST_EXCEPTION;
import static com.example.authentication.model.constants.ExceptionConstants.NOT_FOUND_EXCEPTION;
import static com.example.authentication.model.constants.ExceptionConstants.UNEXPECTED_EXCEPTION;


@Log4j2
@RequiredArgsConstructor
public class FeignErrorDecoder implements ErrorDecoder {

  private final ObjectMapper objectMapper;
  private final ErrorDecoder defaultErrorDecoder = new Default();

  @Override
  public Exception decode(String methodKey, Response response) {
    ExceptionResponse message;
    try (InputStream body = response.body().asInputStream()) {
      message = objectMapper.readValue(body, ExceptionResponse.class);
    } catch (IOException e) {
      log.error("FeignErrorDecoder.decode().ERROR {}", e.getMessage());
      return new AppException(UNEXPECTED_EXCEPTION, e.getMessage());
    }

    return switch (response.status()) {
      case 400 -> new AppException(BAD_REQUEST_EXCEPTION, message.getErrorMessage());
      case 404 -> new AppException(NOT_FOUND_EXCEPTION, message.getErrorMessage());
      default -> defaultErrorDecoder.decode(methodKey, response);
    };
  }

}

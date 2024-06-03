package com.example.userms.exception.handler;

import com.example.userms.model.constants.ExceptionConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Data
@SuperBuilder
@NoArgsConstructor
public class ExceptionResponse {
  private String userMessage;
  private String errorMessage;
  private List<ValidationErrorResponse> validationErrors;
  @Builder.Default
  @JsonFormat(shape = STRING, pattern = "dd-MM-yyyy hh:mm:ss")
  private Date timestamp = new Date();

  public ExceptionResponse(ExceptionConstants exceptionConstants, String errorMessage) {
    this.errorMessage = errorMessage;
    this.userMessage = exceptionConstants.getUserMessage();
  }
}

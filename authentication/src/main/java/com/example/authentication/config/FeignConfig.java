package com.example.authentication.config;

import com.example.authentication.exception.decoder.FeignErrorDecoder;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

public class FeignConfig {

  @Bean
  public ErrorDecoder errorDecoder(ObjectMapper objectMapper) {
    return new FeignErrorDecoder(objectMapper);
  }

}

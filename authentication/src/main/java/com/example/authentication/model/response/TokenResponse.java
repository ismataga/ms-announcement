package com.example.authentication.model.response;

import lombok.Builder;

@Builder
public record TokenResponse(String accessToken, String refreshToken) {

}
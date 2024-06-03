package com.example.authentication.model.response;

import lombok.Builder;

@Builder
public record ValidateTokenResponse(Long userId) {
}

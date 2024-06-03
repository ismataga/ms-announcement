package com.example.authentication.client.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record UserInfoClientRequest(@Positive(message = "user-id is mandatory") Long userId,
                                    @NotBlank(message = "full name is mandatory") String fullName,
                                    @NotBlank(message = "username is mandatory") String username) {
}

package com.example.authentication.model.request;


import com.example.authentication.annotations.PasswordValueMatch;
import com.example.authentication.annotations.ValidPassword;
import jakarta.validation.constraints.NotBlank;

@PasswordValueMatch.List({
    @PasswordValueMatch(
        field = "password",
        fieldMatch = "confirmPassword",
        message = "Passwords do not match!"
    )
})
public record UserInfoRequest(@NotBlank(message = "full name is mandatory") String fullName,
                              @NotBlank(message = "username is mandatory") String username,
                               String password,
                               String confirmPassword) {
}

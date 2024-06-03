package com.example.authentication.model.request;


import com.example.authentication.annotations.ValidEmail;
import com.example.authentication.annotations.ValidPassword;

public record SignInRequest(@ValidEmail String email, @ValidPassword String password) {
}

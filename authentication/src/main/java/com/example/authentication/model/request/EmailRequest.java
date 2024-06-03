package com.example.authentication.model.request;


import com.example.authentication.annotations.ValidEmail;
import lombok.Builder;

@Builder
public record EmailRequest(@ValidEmail String email) {
}

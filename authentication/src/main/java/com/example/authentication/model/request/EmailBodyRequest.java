package com.example.authentication.model.request;

import lombok.Builder;

@Builder
public record EmailBodyRequest(String receiverEmail, String verificationCode) {

}

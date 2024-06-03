package com.example.authentication.model.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AuthConstants {

  public static final String RSA = "RSA";
  public static final String AUTH_CACHE_PXS = "ms-auth:";
  public static final String ISSUER = "ms-auth";
  public static final Integer KEY_SIZE = 2048;
  public static final Long TOKEN_EXPIRE_COUNT = 15L;

}
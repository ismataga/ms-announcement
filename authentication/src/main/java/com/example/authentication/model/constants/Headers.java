package com.example.authentication.model.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Headers {

  public static final String AUTHORIZATION = "Authorization";
    public static final String USER_ID = "X-User-Id";
  public static final String REFRESH_TOKEN = "X-Refresh-Token";

}

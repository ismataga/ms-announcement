package com.example.authentication.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class AuthCacheData implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  private String publicKey;
  private AccessTokenClaimsSet accessTokenClaimsSet;
}
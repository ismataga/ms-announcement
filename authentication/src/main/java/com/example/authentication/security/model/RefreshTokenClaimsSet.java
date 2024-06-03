package com.example.authentication.security.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class RefreshTokenClaimsSet {
  private Long userId;
  private Date expirationTime;
  private Integer count;
  private String iss;
}

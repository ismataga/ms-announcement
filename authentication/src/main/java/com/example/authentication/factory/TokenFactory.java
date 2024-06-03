package com.example.authentication.factory;




import com.example.authentication.security.model.AccessTokenClaimsSet;
import com.example.authentication.security.model.RefreshTokenClaimsSet;

import java.util.Date;

import static com.example.authentication.model.constants.AuthConstants.ISSUER;


public interface TokenFactory {
    static AccessTokenClaimsSet buildAccessTokenClaimsSet(Long userId, Date expirationTime) {
        return AccessTokenClaimsSet.builder()
                .iss(ISSUER)
                .userId(userId)
                .createdTime(new Date())
                .expirationTime(expirationTime)
                .build();
    }

    static RefreshTokenClaimsSet buildRefreshTokenClaimsSet(Long userId,
                                                            Integer refreshTokenExpirationCount,
                                                            Date expirationTime) {
        return RefreshTokenClaimsSet.builder()
                .iss(ISSUER)
                .userId(userId)
                .expirationTime(expirationTime)
                .count(refreshTokenExpirationCount)
                .build();
    }

}
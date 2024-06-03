package com.example.authentication.service;


import com.example.authentication.cache.CacheUtil;
import com.example.authentication.config.ApplicationProperties;
import com.example.authentication.exception.AppException;
import com.example.authentication.model.response.TokenResponse;
import com.example.authentication.model.response.ValidateTokenResponse;
import com.example.authentication.security.jwt.JwtUtil;
import com.example.authentication.security.model.AuthCacheData;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

import static com.example.authentication.factory.TokenFactory.buildAccessTokenClaimsSet;
import static com.example.authentication.factory.TokenFactory.buildRefreshTokenClaimsSet;
import static com.example.authentication.model.constants.AuthConstants.AUTH_CACHE_PXS;
import static com.example.authentication.model.constants.AuthConstants.TOKEN_EXPIRE_COUNT;
import static com.example.authentication.model.constants.ExceptionConstants.REFRESH_TOKEN_COUNT_EXPIRED;
import static com.example.authentication.model.constants.ExceptionConstants.REFRESH_TOKEN_EXPIRED;
import static com.example.authentication.model.constants.ExceptionConstants.TOKEN_EXPIRED;
import static com.example.authentication.model.constants.ExceptionConstants.USER_UNAUTHORIZED;
import static java.util.concurrent.TimeUnit.DAYS;

@Log4j2
@Service
@RequiredArgsConstructor
public class TokenService {

    private final JwtUtil jwtUtil;
    private final CacheUtil<AuthCacheData> cacheUtil;
    private final ApplicationProperties appProperties;

    public TokenResponse generateToken(Long userId, Integer refreshTokenExpCount) {
        var accessExpTime = jwtUtil.generateSessionExpTime(appProperties.getAccessTokenExpTime());
        var refreshExpTime = jwtUtil.generateSessionExpTime(appProperties.getRefTokenExpTime());

        var aTokenClaimsSet = buildAccessTokenClaimsSet(userId, accessExpTime);
        var rTokenClaimsSet = buildRefreshTokenClaimsSet(userId, refreshTokenExpCount, refreshExpTime);

        var keyPair = jwtUtil.generateKeyPair();
        var authCacheData = AuthCacheData.of(jwtUtil.encodePublicKey(keyPair), aTokenClaimsSet);

        cacheUtil.saveToCache(AUTH_CACHE_PXS + userId, authCacheData, TOKEN_EXPIRE_COUNT, DAYS);

        var accessToken = jwtUtil.generateToken(aTokenClaimsSet, keyPair.getPrivate());
        var refreshToken = jwtUtil.generateToken(rTokenClaimsSet, keyPair.getPrivate());

        return new TokenResponse(accessToken, refreshToken);
    }

    public TokenResponse refreshTokens(String refreshToken) {
        var refreshTokenClaimsSet = jwtUtil.getClaimsFromRefreshToken(refreshToken);
        var userId = refreshTokenClaimsSet.getUserId();

        AuthCacheData authCacheData = cacheUtil.getBucket(AUTH_CACHE_PXS + userId);

        if (authCacheData == null) {
            throw new AppException(USER_UNAUTHORIZED);
        }

        jwtUtil.verifyToken(refreshToken, jwtUtil.decodePublicKey(authCacheData.getPublicKey()));

        if (jwtUtil.isRefreshTokenTimeExpired(refreshTokenClaimsSet)) {
            throw new AppException(REFRESH_TOKEN_EXPIRED);
        }

        if (jwtUtil.isRefreshTokenCountExpired(refreshTokenClaimsSet)) {
            throw new AppException(REFRESH_TOKEN_COUNT_EXPIRED);
        }

        return generateToken(userId, refreshTokenClaimsSet.getCount() - 1);
    }

    public ValidateTokenResponse validateToken(String accessToken) {
        var userId = jwtUtil.getClaimsFromAccessToken(accessToken).getUserId();
        AuthCacheData authCacheData = cacheUtil.getBucket(AUTH_CACHE_PXS + userId);

        if (authCacheData == null) {
            throw new AppException(TOKEN_EXPIRED);
        }
         jwtUtil.verifyToken(accessToken, jwtUtil.decodePublicKey(authCacheData.getPublicKey()));

        if (jwtUtil.isTokenExpired(authCacheData.getAccessTokenClaimsSet().getExpirationTime())) {
            throw new AppException(TOKEN_EXPIRED);
        }


        return new ValidateTokenResponse(userId);    }

    public Authentication getAuthentication(JWTClaimsSet claims) {
        List<String> authorities = (List<String>) claims.getClaim("authorities");
        List<SimpleGrantedAuthority> authorityList = authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(claims.getSubject(), null, authorityList);

    }


}
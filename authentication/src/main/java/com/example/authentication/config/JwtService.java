package com.example.authentication.config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtService {
    @Value("${jwt.secret-key}")
    public String secretKey;

    public Claims tokenParser(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) generateKey(secretKey))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String generateToken(User user) {
        return Jwts.builder()
                .signWith(generateKey(secretKey))
                .issuedAt(new Date())
                .expiration(Date.from(Instant.now().plus(Duration.ofMinutes(5))))
                .subject(user.getUsername())
                .claim("authority", "USER")
                .compact();
    }


    public Authentication getAuthentication(Claims claims) {
        List<String> authorities = (List<String>) claims.get("authorities");
        List<SimpleGrantedAuthority> authorityList = authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(claims.getSubject(), null, authorityList);

    }

    private Key generateKey(String secretKey) {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    public <T> T extractClaims(String token, Function<Claims, T> claimsTFunction) {
        Claims claims = tokenParser(token);
        return claimsTFunction.apply(claims);
    }

}

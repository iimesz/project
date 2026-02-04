package com.khaled.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${JWT_SECRET}")
    private String jwtSecret;

    @Value("${JWT_ACCESS_EXPIRATION}")
    private long accessTokenExpiration;

    @Value("${JWT_REFRESH_EXPIRATION}")
    private long refreshTokenExpiration;

    // Decode Base64 secret and create signing key
    private SecretKey getSigningKey() {
        byte[] keyBytes = Base64.getDecoder().decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Generate Access Token with expiration
    public String generateAccessToken(String email) {
        return generateToken(email, accessTokenExpiration);
    }

    // Generate Refresh Token with expiration
    public String generateRefreshToken(String email) {
        return generateToken(email, refreshTokenExpiration);
    }

    // General token generation method
    private String generateToken(String email, long expirationTime) {
        Date now = new Date();
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expirationTime))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Validate the JWT token integrity and expiration
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token); // throws if invalid or expired
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // Invalid JWT or expired token
            return false;
        }
    }

    // Extract email (subject) from the JWT token
    public String getEmailFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}

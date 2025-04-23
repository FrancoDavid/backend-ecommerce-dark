package com.darkdev.ecommerce.ecommerce_backend.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    private final String SECRET_KEY="lol";

    public String generateToken(String email) {
        return JWT.create()
                .withSubject(email)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    public String extractInfo(String token) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(SECRET_KEY))
                .build()
                .verify(token);
        return decodedJWT.getSubject();
    }

    public boolean isTokenValid(String token, String email) {
        try {
            String tokenEmail = extractInfo(token);
            return tokenEmail.equals(email);
        } catch (RuntimeException e) {
            return false;
        }
    }
}

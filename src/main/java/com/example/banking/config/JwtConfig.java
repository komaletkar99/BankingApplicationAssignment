package com.example.banking.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expirationMs}")
    private long expirationMs;

    // getters
    public String getSecretKey() {
        return secretKey;
    }

    public long getExpirationMs() {
        return expirationMs;
    }
}

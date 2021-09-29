package com.example.softwareengineeringbackend.util.jwt;

import com.example.softwareengineeringbackend.jwt.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class JwtServiceTest {
    private JwtService jwtService;
    private final Long id = 999L;
    private final String email = "test@bearer.token";

    @BeforeEach
    void init() {
        jwtService = new JwtService();
    }

    @Test
    void generateTokenShouldReturnBearerToken() {
        String token = jwtService.generateToken(id, email, null);

        Assertions.assertNotNull(token);
        Assertions.assertTrue(token.length() > 0);
    }

    @Test
    void generateTokenShouldReturnBearerTokenWithNewExpirationIfClaimsNotNull() {
        Claims claims = new DefaultClaims();

        String token = jwtService.generateToken(id, email, claims);

        Assertions.assertNotNull(token);
        Assertions.assertTrue(token.length() > 0);
    }

    @Test
    void verifyTokenShouldReturnNullIfPrefixInvalid() {
        String token = "bad_token";

        Claims claims = jwtService.verifyToken(token);

        Assertions.assertNull(claims);
    }

    @Test
    void verifyTokenShouldReturnNullIfException() {
        String token = "Bearer bad_token";

        Claims claims = jwtService.verifyToken(token);

        Assertions.assertNull(claims);
    }

    @Test
    void verifyTokenShouldReturnClaimsIfTokenIsValid() {
        String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdXRoRGF0ZSI6MTYzMjkzMzYxNjU1MiwiZW1haWwiOiJlbWFpbEBlbWFpbC5jb20iLCJpZCI6MSwiZXhwaXJhdGlvbiI6MTYzMzAyMDAxNjU1Mn0.XQG1trk47NvxFiNW96mhJEpp4mbLDTglbZDl9gpzbms";

        Claims claims = jwtService.verifyToken(token);

        Assertions.assertNotNull(claims);
    }
}

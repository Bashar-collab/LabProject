package com.example.laboratory.JWT;

import com.example.laboratory.Config.SecurityConfig.CustomUserDetails;
import com.example.laboratory.Service.CustomUserDetailsService;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtTokenProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
    /*
        jwtSecret: This is the secret key used to sign the JWT. It ensures that the JWT has not been tampered with and is from a trusted source.
        jwtExpirationMs: This is the duration for which the JWT is valid, in milliseconds. It determines how long a token remains usable before it expires.
        jwtCookie: This is the name of the cookie where the JWT is stored. It allows the server to manage authentication by setting and reading JWT cookies.
    */
    @Value("${lab.app.jwtSecretKey}")
    private String jwtSecretKey;

    @Value("${lab.app.jwtRefreshSecretKey}")
    private String jwtRefreshSecretKey;


    private final CustomUserDetailsService customUserDetailsService;

    public JwtTokenProvider(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }
//    @Autowired

    // Generate Access Token
    public String generateAccessToken(String phoneNumber) {
        logger.info("Access Token String: {}", phoneNumber);
        // 15 minutes
        long ACCESS_TOKEN_VALIDITY = 1000 * 60 * 15;
        return createToken(phoneNumber, ACCESS_TOKEN_VALIDITY, jwtSecretKey);
    }

    // Generate Refresh Token
    public String generateRefreshToken(String phoneNumber) {
        // 7 days
        long REFRESH_TOKEN_VALIDITY = 1000 * 60 * 60 * 24 * 7;
        return createToken(phoneNumber, REFRESH_TOKEN_VALIDITY, jwtRefreshSecretKey);
    }
    public String createToken(String subject, long validity, String secretKey) {
        Map<String, Object> claims = new HashMap<>();
        CustomUserDetails userDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(subject);
        claims.put("profileType", userDetails.getUsers().getProfileType());
        claims.put("isAdmin", userDetails.getUsers().isAdmin());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsers().getPhoneNumber())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + validity))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Claims extractClaims(String token) {
        return Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token).getBody();
    }

    public boolean isTokenValid(String token) {
        try {
            extractClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String extractPhoneNumber(String token) {
        return extractClaims(token).getSubject();
    }

    public boolean extractIsAdmin(String token) {
        return (Boolean) extractClaims(token).get("isAdmin");
    }

    public String extractProfileType(String token) {
        return (String) extractClaims(token).get("profileType");
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token).getBody();
    }

    public Boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // Handle token expiration, signature errors, etc.
            return false;
        }
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }


    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

        public String expireToken(String token) {
            // Parse the existing token
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtSecretKey)
                    .parseClaimsJws(token)
                    .getBody();

            // Set the expiration time to the past
            claims.setExpiration(new Date(System.currentTimeMillis() - 10000)); // Set to 1 second in the past

            // Re-sign the token with the updated claims
            return Jwts.builder()
                    .setClaims(claims)
                    .signWith(SignatureAlgorithm.HS256, jwtSecretKey)
                    .compact();
        }
    }

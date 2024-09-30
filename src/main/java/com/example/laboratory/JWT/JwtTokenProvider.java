package com.example.laboratory.JWT;

import com.example.laboratory.Config.SecurityConfig.CustomUserDetails;
import com.example.laboratory.EntityResolver.ProfileResolver.Factory.ProfileResolverFactory;
import com.example.laboratory.EntityResolver.ProfileResolver.ProfileResolver;
import com.example.laboratory.Service.CustomUserDetailsService;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtTokenProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${lab.app.jwtSecretKey}")
    private String jwtSecretKey;

    @Value("${lab.app.jwtRefreshSecretKey}")
    private String jwtRefreshSecretKey;

    @Autowired
    private ProfileResolverFactory profileResolverFactory;
    private final CustomUserDetailsService customUserDetailsService;

    public JwtTokenProvider(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    // Generate Access Token
    public String generateAccessToken(String phoneNumber) {
        logger.info("Access Token String: {}", phoneNumber);
        // 15 minutes (7 days) 1000 * 60 * 15
        long ACCESS_TOKEN_VALIDITY = 1000 * 60 * 2;
        return createToken(phoneNumber, ACCESS_TOKEN_VALIDITY, jwtSecretKey);
    }

    // Generate Refresh Token
    public String generateRefreshToken(String phoneNumber) {
        // 7 days
        logger.info("Refresh token is generated");
        long REFRESH_TOKEN_VALIDITY = 1000 * 60 * 60 * 24 * 7;
        return createToken(phoneNumber, REFRESH_TOKEN_VALIDITY, jwtSecretKey);
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
            Claims claims = extractClaims(token);
            return validateClaims(claims);
        } catch (ExpiredJwtException e) {
            // Extract claims from the expired token
            Claims expiredClaims = e.getClaims();
            logger.warn("Token is expired, but claims are extracted");
            return validateClaims(expiredClaims);
        } catch (Exception e) {
            logger.error("Invalid Token: " + e);
            return false;
        }
    }

    private boolean validateClaims(Claims claims) {
        // Check if the token contains profileType and isAdmin attributes
        String profileType = claims.get("profileType", String.class);
        Boolean isAdmin = claims.get("isAdmin", Boolean.class);

        logger.info("Profile Type when validating token: " + profileType);
        logger.info("isAdmin status when validating token: " + isAdmin);

        // Validate profileType and isAdmin
        /*
        if (profileType == null || isAdmin == null) {
            return false; // Invalid if either is missing
        }
         */
        if (!isAdmin)
            if (profileType == null)
                return false;
        // Additional validation for specific profile types
        // Token is valid if either isAdmin is true, profileType is valid, or profileType is "Lab Manager"
        return isAdmin || isValidProfileType(profileType) || profileType.equals("Lab Manager");
    }

    public boolean isValidProfileType(String profileType) {
        // Use the ProfileResolverFactory to check if a valid resolver exists
        ProfileResolver resolver = profileResolverFactory.getResolver(profileType);
        return resolver != null;
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
            logger.info("validating token");
            Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            logger.info("Token is invalid " + e);
            // Handle token expiration, signature errors, etc.
            return false;
        }
    }

    public Boolean isTokenExpired(String token) {
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

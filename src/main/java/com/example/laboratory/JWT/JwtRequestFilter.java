package com.example.laboratory.JWT;

import com.example.laboratory.Service.CustomUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);

    private final JwtTokenProvider jwtUtils;

    private final UserDetailsService userDetailsService;

    private final CustomUserDetailsService customUserDetailsService;
    public JwtRequestFilter(JwtTokenProvider jwtUtils, UserDetailsService userDetailsService, CustomUserDetailsService customUserDetailsService) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            final String authorizationHeader = request.getHeader("Authorization");

            String phoneNumber = null;
            String jwt = null;
/*
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                jwt = authorizationHeader.substring(7);
                phoneNumber = jwtUtils.extractPhoneNumber(jwt);
                logger.info("{} is extracted", phoneNumber);
            }
 */

            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                jwt = authorizationHeader.substring(7);
                try
                {
                    phoneNumber = jwtUtils.extractPhoneNumber(jwt);
                    logger.info("{} is extracted", phoneNumber);
                } catch (ExpiredJwtException e)
                {
                    logger.warn("Token is expired but extracting claims");
                    phoneNumber = e.getClaims().getSubject(); // Extract claims even if the token is expired
                }
            }

            if (phoneNumber != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                logger.info("Validating for {}", phoneNumber);

                UserDetails userDetails = customUserDetailsService.loadUserByUsername(phoneNumber);
                logger.info("{} is loaded", phoneNumber);
                logger.info("User's details: {}", userDetails.getUsername());
                logger.info("User's authorizes: {}", userDetails.getAuthorities());
                if (jwtUtils.isTokenValid(jwt)) {
                    // Create a new authentication token if the JWT is valid
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    logger.info("{} is validated", phoneNumber);
                }
            }
        } catch (Exception e)
        {
            logger.error("Cannot set user authentication: " + e);
        }
        chain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request)
    {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7, headerAuth.length());
        }

        return null;
    }

}

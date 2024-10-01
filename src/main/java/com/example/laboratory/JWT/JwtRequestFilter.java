package com.example.laboratory.JWT;

import com.example.laboratory.Service.CustomUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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
    // Get the Authorization header from the request
    final String authorizationHeader = request.getHeader("Authorization");
    final String requestURI = request.getRequestURI();

    String phoneNumber = null;
    String jwt = null;

    // Check if the Authorization header is present and starts with "Bearer "
    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
        jwt = authorizationHeader.substring(7);
            if (jwtUtils.isTokenValid(jwt)) {
                phoneNumber = jwtUtils.extractPhoneNumber(jwt);

                logger.info("Refresh token request: JWT is valid for {}", phoneNumber);
            } else {
                logger.warn("Invalid JWT token for refresh.");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
            try
            {
                if (jwtUtils.isTokenValid(jwt)) {
                    phoneNumber = jwtUtils.extractPhoneNumber(jwt);

                    // Load user details using the extracted phone number
                    UserDetails userDetails = customUserDetailsService.loadUserByUsername(phoneNumber);

                    // Log the process for debugging purposes
                    logger.info("Validating for {}", phoneNumber);
                    logger.info("{} is loaded", phoneNumber);
                    logger.info("User's details: {}", userDetails.getUsername());
                    logger.info("User's authorities: {}", userDetails.getAuthorities());

                    // Create an authentication token if the JWT is valid
                    if (phoneNumber != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                        // Set the authentication in the SecurityContext
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                        logger.info("{} is validated and authenticated", phoneNumber);
                    } else {
                        logger.warn("Invalid JWT token.");
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        return;
                    }
                }
            } catch (ExpiredJwtException e) {
                // Handle expired JWT token for non-refresh endpoints
                logger.warn("Token is expired");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }




// Proceed with the filter chain
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

package com.example.laboratory.Config.SecurityConfig;

import com.example.laboratory.models.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetails.class);

    private final Users users;

    public CustomUserDetails(Users user)
    {
        this.users = user;
    }


    public Users getUsers() {
        return users;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (users.isAdmin()) {
            return Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else if(users.getProfileType() != null){
            return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + users.getProfileType().toUpperCase()));
        } else {
            return Collections.singletonList(new SimpleGrantedAuthority("ROLE_LAB_MANAGER"));
        }
    }

    public String getPhoneNumber() { return users.getPhoneNumber();}
    @Override
    public String getPassword() {
        return users.getPassword();
    }

    @Override
    public String getUsername() {
        return users.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}

package com.example.demo.services;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.model.User;

import lombok.Data;

@Data
public class UserDetailCustome implements  UserDetails{

    private User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override
    public String getPassword() {
        return "{noop}"+user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();

    }
    
}

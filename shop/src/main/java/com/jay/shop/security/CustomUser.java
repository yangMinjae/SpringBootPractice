package com.jay.shop.security;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter

public class CustomUser extends User {
    private final String name;
    @Builder
    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities, String name) {
        super(username, password, authorities);
        this.name = name;
    }
}

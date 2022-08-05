package com.example.demo.Model.enummeration;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_UCENIK, ROLE_ADMINISTRATOR;

    @Override
    public String getAuthority() {
        return name();
    }
}

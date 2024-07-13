package com.web3.with.security.model;

import com.web3.with.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@AllArgsConstructor
public @Data class RegistrationDto {
    private String username;
    private String password;
    private String email;
    private String role;
}

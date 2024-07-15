package com.web3.with.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public @Data class RegistrationDto {
    private String username;
    private String password;
    private String email;
    private String role;
}

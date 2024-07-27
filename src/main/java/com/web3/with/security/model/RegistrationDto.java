package com.web3.with.security.model;

import com.web3.with.security.model.role.RoleName;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Class for registration data. TODO move to openAPI?
 */
@AllArgsConstructor
public @Data class RegistrationDto {

    private String username;

    private String password;

    private String email;

    private RoleName role;

}

package com.web3.with.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Class for authentication data. TODO move to openAPI?
 */
@AllArgsConstructor
public @Data class AuthDto {

    private String email;

    private String password;

}

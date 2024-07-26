package com.web3.with.security.securityResponse;

import com.web3.with.security.securityResponse.base.BaseSecurityResponse;
import org.springframework.http.HttpStatus;

/**
 * Response for security operations.
 */
public class AppSecurityResponse extends BaseSecurityResponse {

    public AppSecurityResponse(HttpStatus status, String message) {
        super(status, message);
    }

}

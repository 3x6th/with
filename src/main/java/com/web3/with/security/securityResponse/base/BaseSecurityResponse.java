package com.web3.with.security.securityResponse.base;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * Base class for security responses.
 */
@Getter
@Setter
public abstract class BaseSecurityResponse {

    private HttpStatus status;
    private String message;
    private LocalDateTime timestamp;

    public BaseSecurityResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

}

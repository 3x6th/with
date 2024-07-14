package com.web3.with.security.securityResponse.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

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

package com.web3.with.security.securityResponse.base;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class BaseSecurityResponse {
    private int status;
    private String message;
    private LocalDateTime timestamp;

    public BaseSecurityResponse(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}

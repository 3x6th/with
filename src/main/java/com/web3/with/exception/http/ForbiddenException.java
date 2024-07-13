package com.web3.with.exception.http;

import com.web3.with.exception.http.base.AbstractExceptionHttpHandler;

public class ForbiddenException extends AbstractExceptionHttpHandler {
    public ForbiddenException(String message) {
        super(message);
    }
}

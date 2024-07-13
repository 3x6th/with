package com.web3.with.exception.http;

import com.web3.with.exception.http.base.AbstractExceptionHttpHandler;

public class BadRequestException extends AbstractExceptionHttpHandler {
    public BadRequestException(String message) {
        super(message);
    }
}

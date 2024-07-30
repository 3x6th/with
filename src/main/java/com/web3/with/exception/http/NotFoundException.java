package com.web3.with.exception.http;

import com.web3.with.exception.http.base.AbstractExceptionHttpHandler;

public class NotFoundException extends AbstractExceptionHttpHandler {

    public NotFoundException(String message) {
        super(message);
    }

}

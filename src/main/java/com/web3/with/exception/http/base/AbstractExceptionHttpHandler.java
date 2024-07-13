package com.web3.with.exception.http.base;

import java.io.Serial;
import java.io.Serializable;

public class AbstractExceptionHttpHandler extends RuntimeException implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public AbstractExceptionHttpHandler(String message) {
        super(String.format("%s", message));
    }
}

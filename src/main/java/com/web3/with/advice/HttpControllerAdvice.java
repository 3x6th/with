package com.web3.with.advice;

import com.web3.with.exception.http.ForbiddenException;
import com.web3.with.exception.http.NotFoundException;
import com.web3.with.security.securityResponse.AppSecurityResponse;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * HttpControllerAdvice for handling exceptions.
 */
@RestControllerAdvice
public class HttpControllerAdvice {

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public AppSecurityResponse handleForbiddenException(ForbiddenException e) {
        return new AppSecurityResponse(HttpStatus.FORBIDDEN.value(), e.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AppSecurityResponse badRequestException(BadRequestException e) {
        return new AppSecurityResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public AppSecurityResponse handleNotFoundException(NotFoundException e) {
        return new AppSecurityResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public AppSecurityResponse handleNullPoint(NullPointerException e) {
        return new AppSecurityResponse(HttpStatus.NO_CONTENT.value(), e.getMessage());
    }

}

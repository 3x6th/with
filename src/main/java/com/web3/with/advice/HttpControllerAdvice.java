package com.web3.with.advice;

import com.web3.with.exception.http.ForbiddenException;
import com.web3.with.exception.http.NotFoundException;
import java.time.ZonedDateTime;
import org.apache.coyote.BadRequestException;
import org.openapitools.model.WithAppExceptionRs;
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
    public WithAppExceptionRs handleForbiddenException(ForbiddenException e) {
        return new WithAppExceptionRs(HttpStatus.FORBIDDEN.toString(), e.getMessage(), ZonedDateTime.now());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public WithAppExceptionRs badRequestException(BadRequestException e) {
        return new WithAppExceptionRs(HttpStatus.BAD_REQUEST.toString(), e.getMessage(), ZonedDateTime.now());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public WithAppExceptionRs handleNotFoundException(NotFoundException e) {
        return new WithAppExceptionRs(HttpStatus.NOT_FOUND.toString(), e.getMessage(), ZonedDateTime.now());
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public WithAppExceptionRs handleNullPoint(NullPointerException e) {
        return new WithAppExceptionRs(HttpStatus.NO_CONTENT.toString(), e.getMessage(), ZonedDateTime.now());
    }

}

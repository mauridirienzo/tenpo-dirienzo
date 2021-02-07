package com.interview.tenpo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PasswordMissmatchException extends RuntimeException
{
    public PasswordMissmatchException(String exception) {
        super(exception);
    }
}
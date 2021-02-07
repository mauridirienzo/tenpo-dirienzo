package com.interview.tenpo.exceptions;

public class JwtGeneralException extends Exception{
    public JwtGeneralException(String errorMessage) {
        super(errorMessage);
    }
}

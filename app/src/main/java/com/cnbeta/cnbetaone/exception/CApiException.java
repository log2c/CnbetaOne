package com.cnbeta.cnbetaone.exception;

public class CApiException extends RuntimeException {
    private int errorCode;

    public CApiException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}

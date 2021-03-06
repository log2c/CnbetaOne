package com.log2c.cnbetaone.exception;

public class CApiException extends RuntimeException {
    private int errorCode;

    public CApiException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}

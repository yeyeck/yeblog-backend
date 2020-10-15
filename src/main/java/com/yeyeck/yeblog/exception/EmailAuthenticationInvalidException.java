package com.yeyeck.yeblog.exception;

public class EmailAuthenticationInvalidException extends RuntimeException {

    public EmailAuthenticationInvalidException() {
        super();
    }
    public EmailAuthenticationInvalidException(String message) {
        super(message);
    }
}

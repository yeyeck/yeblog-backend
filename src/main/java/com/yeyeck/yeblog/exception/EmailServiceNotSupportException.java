package com.yeyeck.yeblog.exception;

public class EmailServiceNotSupportException extends RuntimeException {

    public EmailServiceNotSupportException() {
        super();
    }

    public EmailServiceNotSupportException(String message) {
        super(message);
    }
}

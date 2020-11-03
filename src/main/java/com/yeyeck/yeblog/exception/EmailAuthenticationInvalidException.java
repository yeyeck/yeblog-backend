package com.yeyeck.yeblog.exception;

public class EmailAuthenticationInvalidException extends RuntimeException {


    private static final long serialVersionUID = 5199233873929613677L;

    public EmailAuthenticationInvalidException() {
        super();
    }
    public EmailAuthenticationInvalidException(String message) {
        super(message);
    }
}

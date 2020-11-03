package com.yeyeck.yeblog.exception;

public class SystemLimitException extends RuntimeException{

    private static final long serialVersionUID = 3840058487471558581L;

    public SystemLimitException(String message) {
        super(message);
    }

    public SystemLimitException(String message, Throwable cause) {
        super(message, cause);
    }
}

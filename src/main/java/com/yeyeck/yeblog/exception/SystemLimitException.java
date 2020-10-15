package com.yeyeck.yeblog.exception;

public class SystemLimitException extends RuntimeException{
    public SystemLimitException(String message) {
        super(message);
    }

    public SystemLimitException(String message, Throwable cause) {
        super(message, cause);
    }
}

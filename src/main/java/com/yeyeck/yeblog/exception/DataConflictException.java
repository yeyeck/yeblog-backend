package com.yeyeck.yeblog.exception;

public class DataConflictException extends RuntimeException {

    public DataConflictException(String message) {
        super(message);
    }

    public DataConflictException(String message, Throwable cause) {
        super(message, cause);
    }
}

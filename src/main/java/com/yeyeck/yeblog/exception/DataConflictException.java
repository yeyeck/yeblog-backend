package com.yeyeck.yeblog.exception;

public class DataConflictException extends RuntimeException {

    private static final long serialVersionUID = -2928126398879788559L;

    public DataConflictException(String message) {
        super(message);
    }

    public DataConflictException(String message, Throwable cause) {
        super(message, cause);
    }
}

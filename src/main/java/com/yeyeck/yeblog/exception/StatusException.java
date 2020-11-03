package com.yeyeck.yeblog.exception;

public class StatusException extends RuntimeException {


    private static final long serialVersionUID = -8023178985991908927L;

    public StatusException(String message) {
        super(message);
    }

    public StatusException(String message, Throwable cause) {
        super(message, cause);
    }
}

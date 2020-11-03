package com.yeyeck.yeblog.exception;

public class NoSuchDataException extends RuntimeException{


    private static final long serialVersionUID = -5988386390390480554L;

    public NoSuchDataException(String message) {
        super(message);
    }

    public NoSuchDataException(String message, Throwable cause) {
        super(message, cause);
    }
}

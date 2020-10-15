package com.yeyeck.yeblog.exception;

public class ParamException extends RuntimeException{

    public ParamException(String message) {
        super(message);
    }

    public ParamException(String message, Throwable cause) {
        super(message, cause);
    }
}

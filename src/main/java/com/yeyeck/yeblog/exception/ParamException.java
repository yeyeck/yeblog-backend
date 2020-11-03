package com.yeyeck.yeblog.exception;

public class ParamException extends RuntimeException{

    private static final long serialVersionUID = -7197103105320489867L;

    public ParamException(String message) {
        super(message);
    }

    public ParamException(String message, Throwable cause) {
        super(message, cause);
    }
}

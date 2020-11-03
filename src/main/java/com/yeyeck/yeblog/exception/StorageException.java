package com.yeyeck.yeblog.exception;

public class StorageException extends RuntimeException {


    private static final long serialVersionUID = 3700861036250202651L;

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}

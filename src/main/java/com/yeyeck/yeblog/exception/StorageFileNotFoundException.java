package com.yeyeck.yeblog.exception;

public class StorageFileNotFoundException extends RuntimeException {


    private static final long serialVersionUID = -2353340298354369123L;

    public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

package com.system.exceptions;

public class DataBaseSqlRuntimeException extends RuntimeException {

    public DataBaseSqlRuntimeException() {
    }

    public DataBaseSqlRuntimeException(String message) {
        super(message);
    }

    public DataBaseSqlRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
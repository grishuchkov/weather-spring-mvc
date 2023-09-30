package ru.grishuchkov.weather.exception;

public class DuplicateLocationException extends RuntimeException{
    public DuplicateLocationException() {
    }

    public DuplicateLocationException(String message) {
        super(message);
    }

    public DuplicateLocationException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateLocationException(Throwable cause) {
        super(cause);
    }

    public DuplicateLocationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

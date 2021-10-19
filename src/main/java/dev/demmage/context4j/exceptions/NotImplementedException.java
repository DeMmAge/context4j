package dev.demmage.context4j.exceptions;

public class NotImplementedException extends RuntimeException {
    public NotImplementedException() {
    }

    public NotImplementedException(String message) {
        super(message);
    }

    public NotImplementedException(String message, Throwable cause) {
        super(message, cause);
    }
}

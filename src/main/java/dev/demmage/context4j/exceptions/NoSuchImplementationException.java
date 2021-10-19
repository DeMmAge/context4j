package dev.demmage.context4j.exceptions;

public class NoSuchImplementationException extends RuntimeException {
    public NoSuchImplementationException() {
    }

    public NoSuchImplementationException(String message) {
        super(message);
    }

    public NoSuchImplementationException(String message, Throwable cause) {
        super(message, cause);
    }
}

package dev.demmage.context4j.exceptions;

public class Context4jException extends RuntimeException {

    public Context4jException() {
        super();
    }

    public Context4jException(String message) {
        super(message);
    }

    public Context4jException(String message, Throwable cause) {
        super(message, cause);
    }
}

package dev.demmage.context4j.exceptions;

public class MultipleImplementationsException extends Context4jException {

    public MultipleImplementationsException() {
        super();
    }

    public MultipleImplementationsException(String message) {
        super(message);
    }

    public MultipleImplementationsException(String message, Throwable cause) {
        super(message, cause);
    }
}
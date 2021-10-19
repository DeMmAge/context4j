package dev.demmage.context4j.exceptions;

public class MultipleImplementationsException extends RuntimeException {
    public MultipleImplementationsException() {
    }

    public MultipleImplementationsException(String s) {
    }

    public MultipleImplementationsException(String message, Throwable cause) {
        super(message, cause);
    }
}
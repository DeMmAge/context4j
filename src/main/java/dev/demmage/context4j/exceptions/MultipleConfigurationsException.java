package dev.demmage.context4j.exceptions;

public class MultipleConfigurationsException extends Context4jException {

    public MultipleConfigurationsException() {
        super();
    }

    public MultipleConfigurationsException(String message) {
        super(message);
    }

    public MultipleConfigurationsException(String message, Throwable cause) {
        super(message, cause);
    }
}

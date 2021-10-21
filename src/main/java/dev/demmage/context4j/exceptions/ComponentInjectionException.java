package dev.demmage.context4j.exceptions;

public class ComponentInjectionException extends Context4jException {

    public ComponentInjectionException() {
        super();
    }

    public ComponentInjectionException(String message) {
        super(message);
    }

    public ComponentInjectionException(String message, Throwable cause) {
        super(message, cause);
    }
}

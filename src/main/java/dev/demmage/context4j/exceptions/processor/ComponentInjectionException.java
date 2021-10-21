package dev.demmage.context4j.exceptions.processor;

import dev.demmage.context4j.exceptions.Context4jException;

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

package dev.demmage.context4j.processor;

public interface ComponentProcessor {

    default Object processBeforeInitialization(Object o) {
        return o;
    }

    default Object processAfterInitialization(Object o) {
        return o;
    }
}
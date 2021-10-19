package dev.demmage.context4j.processor;

public interface ComponentProcessor {

    void processBeforeInitialization(Object o);

    void processAfterInitialization(Object o);
}

package dev.demmage.context4j.processor;

public interface ComponentProcessor {

    // TODO: 20.10.2021
    //void processBeforeInitialization(Object o);

    void processAfterInitialization(Object o);
}

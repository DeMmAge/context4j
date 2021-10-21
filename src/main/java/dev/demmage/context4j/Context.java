package dev.demmage.context4j;

import dev.demmage.context4j.factory.ObjectFactory;
import dev.demmage.context4j.processor.ComponentProcessor;
import dev.demmage.context4j.scan.ComponentsScanner;
import dev.demmage.context4j.scan.ProcessorsScanner;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class Context {

    private final Map<String, Object> initializedComponents = new HashMap<>();

    private final ObjectFactory objectFactory = new ObjectFactory();

    private final Set<ComponentProcessor> processors;

    Context() {
        processors = new ProcessorsScanner().getProcessors();
    }

    public void executePreProcessors() {
        preProcessing();
    }

    public void createComponents() {
        for (Class<?> component : new ComponentsScanner().getComponents()) {
            initializedComponents.put(component.getSimpleName(), objectFactory.createComponent(component));
        }
    }

    public void executePostProcessors() {
        postProcessing();
    }

    private void preProcessing() {
    }

    private void postProcessing() {
        for (Object o : initializedComponents.values()) {
            processors.forEach(p -> p.processAfterInitialization(o));
        }
    }

    public Object getComponent(String name) {
        return initializedComponents.get(name);
    }

    public Collection<Object> getComponents() {
        return initializedComponents.values();
    }
}

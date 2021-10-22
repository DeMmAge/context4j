package dev.demmage.context4j;

import dev.demmage.context4j.factory.ObjectFactory;
import dev.demmage.context4j.processor.ComponentProcessor;
import dev.demmage.context4j.scan.ComponentsScanner;
import dev.demmage.context4j.scan.ProcessorsScanner;

import java.lang.reflect.Constructor;
import java.util.*;

public class Context {

    private final Map<String, Object> initializedComponents = new HashMap<>();

    private final ObjectFactory objectFactory = new ObjectFactory();

    private final Set<ComponentProcessor> processors;

    public Context() {
        processors = new ProcessorsScanner().getProcessors();
    }

    public void executePreProcessors() {
        preProcessing();
    }

    public void createComponents() {
        Set<Class<?>> components = new ComponentsScanner().getComponents();
        Set<Class<?>> componentsWithDefaultConstructors = getComponentsWithDefaultConstructors(components);
        components.removeAll(componentsWithDefaultConstructors);

        componentsWithDefaultConstructors
                .forEach(c -> initializedComponents.put(c.getSimpleName(), objectFactory.createComponent(c)));

        // TODO: 22.10.2021
//        components.forEach(c -> {
//            c -> initializedComponents.put(c.getSimpleName(), objectFactory.createComponent(c))
//        });
    }

    public void executePostProcessors() {
        postProcessing();
    }

    private Set<Class<?>> getComponentsWithDefaultConstructors(Set<Class<?>> components) {
        Set<Class<?>> classes = new HashSet<>();
        components.forEach(c -> {
            Optional<Constructor<?>> constructor = Arrays.stream(c.getConstructors())
                    .filter(ct -> ct.getParameterCount() == 0)
                    .findFirst();

            if (constructor.isPresent()) {
                classes.add(c);
            }
        });
        return classes;
    }

    private void preProcessing() {
        for (Map.Entry<String, Object> entry : initializedComponents.entrySet()) {
            processors.forEach(p -> {
                Object o = p.processBeforeInitialization(entry.getValue());
                initializedComponents.put(entry.getKey(), o);
            });
        }
    }

    private void postProcessing() {
        for (Map.Entry<String, Object> entry : initializedComponents.entrySet()) {
            processors.forEach(p -> {
                Object o = p.processAfterInitialization(entry.getValue());
                initializedComponents.put(entry.getKey(), o);
            });
        }
    }

    public Object getComponent(String name) {
        return initializedComponents.get(name);
    }

    public Collection<Object> getComponents() {
        return initializedComponents.values();
    }
}

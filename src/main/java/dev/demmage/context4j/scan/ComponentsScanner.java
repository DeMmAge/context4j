package dev.demmage.context4j.scan;

import dev.demmage.context4j.annotations.Component;

import java.util.Set;

public class ComponentsScanner extends Scanner {

    public Set<Class<?>> getComponents() {
        return reflections.getTypesAnnotatedWith(Component.class);
    }
}
package dev.demmage.context4j.scan;

import dev.demmage.context4j.annotations.Component;

import java.util.Set;

public class ComponentsScanner {

    public Set<Class<?>> getComponents() {
        return ReflectionsConfigurer.getReflections().getTypesAnnotatedWith(Component.class);
    }
}
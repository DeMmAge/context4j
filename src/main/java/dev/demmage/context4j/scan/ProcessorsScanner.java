package dev.demmage.context4j.scan;

import dev.demmage.context4j.exceptions.Context4jException;
import dev.demmage.context4j.processor.ComponentProcessor;

import java.util.Set;
import java.util.stream.Collectors;

public class ProcessorsScanner {

    public Set<ComponentProcessor> getProcessors() {
        return ReflectionsConfigurer.getReflections().getSubTypesOf(ComponentProcessor.class).stream().map(c -> {
            try {
                return c.newInstance();
            } catch (Exception e) {
                throw new Context4jException("Can't cast processor");
            }
        }).collect(Collectors.toSet());
    }
}

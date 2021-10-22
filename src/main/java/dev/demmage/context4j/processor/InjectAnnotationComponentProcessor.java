package dev.demmage.context4j.processor;

import dev.demmage.context4j.Context4j;
import dev.demmage.context4j.annotations.Inject;
import dev.demmage.context4j.processor.exceptions.ComponentInjectionException;

import java.util.Arrays;

public class InjectAnnotationComponentProcessor implements ComponentProcessor {

    public void processBeforeInitialization(Object o) {

    }

    public void processAfterInitialization(Object o) {
        Arrays.stream(o.getClass().getFields())
                .filter(f -> f.isAnnotationPresent(Inject.class))
                .forEach(f -> {
                    f.setAccessible(true);

                    String type = f.getType().getSimpleName();
                    try {
                        f.set(o, Context4j.getComponent(type));
                    } catch (IllegalAccessException e) {
                        throw new ComponentInjectionException();
                    }
                });
    }
}

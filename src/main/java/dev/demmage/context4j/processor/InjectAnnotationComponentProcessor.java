package dev.demmage.context4j.processor;

import dev.demmage.context4j.Context4j;
import dev.demmage.context4j.annotations.Inject;
import dev.demmage.context4j.processor.exceptions.ComponentInjectionException;

import java.util.Arrays;

public class InjectAnnotationComponentProcessor implements ComponentProcessor {

    public Object processAfterInitialization(Object o) {
        final Class<?> clazz = o.getClass();

        Arrays.stream(clazz.getFields())
                .filter(f -> f.isAnnotationPresent(Inject.class))
                .forEach(f -> {
                    String type = f.getType().getSimpleName();
                    try {
                        f.set(o, Context4j.getComponent(type));
                        //((FieldAccessor) f.invoke(f, o)).set(o, Context4j.getComponent(type));
                    } catch (IllegalAccessException e) {
                        throw new ComponentInjectionException();
                    }
                });

        // TODO: 22.10.2021  
//            Arrays.stream(clazz.getConstructors())
//                    .filter(c -> c.isAnnotationPresent(Inject.class))
//                    .forEach(c -> {
//                        Class<?>[] types = c.getParameterTypes();
//    
//    
//                    });

        return o;
    }
}

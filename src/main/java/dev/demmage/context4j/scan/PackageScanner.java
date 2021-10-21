package dev.demmage.context4j.scan;

import dev.demmage.context4j.annotations.Component;
import dev.demmage.context4j.exceptions.Context4jException;
import dev.demmage.context4j.processor.ComponentProcessor;
import org.reflections.Configuration;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Set;
import java.util.stream.Collectors;

@Deprecated
public class PackageScanner extends Scanner {

    public Configuration getConfiguration() {
        return reflections.getConfiguration();
    }

    public Set<Class<?>> getComponents() {
        return reflections.getTypesAnnotatedWith(Component.class);
    }

    public Set<Class<?>> getAnnotatedTypes(Class<? extends Annotation> annotation) {
        return reflections.getTypesAnnotatedWith(annotation);
    }

    public Set<Field> getAnnotatedFields(Annotation annotation) {
        return reflections.getFieldsAnnotatedWith(annotation);
    }

    public Set<ComponentProcessor> getProcessors() {
        return reflections.getSubTypesOf(ComponentProcessor.class).stream().map(c -> {
            try {
                return (ComponentProcessor) c.newInstance();
            } catch (Exception e) {
                throw new Context4jException("Processor casting error");
            }
        }).collect(Collectors.toSet());
    }
}

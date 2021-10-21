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
public class PackageScanner {

    public Configuration getConfiguration() {
        return ReflectionsConfigurer.getReflections().getConfiguration();
    }

    public Set<Class<?>> getComponents() {
        return ReflectionsConfigurer.getReflections().getTypesAnnotatedWith(Component.class);
    }

    public Set<Class<?>> getAnnotatedTypes(Class<? extends Annotation> annotation) {
        return ReflectionsConfigurer.getReflections().getTypesAnnotatedWith(annotation);
    }

    public Set<Field> getAnnotatedFields(Annotation annotation) {
        return ReflectionsConfigurer.getReflections().getFieldsAnnotatedWith(annotation);
    }

    public Set<ComponentProcessor> getProcessors() {
        return ReflectionsConfigurer.getReflections().getSubTypesOf(ComponentProcessor.class).stream().map(c -> {
            try {
                return (ComponentProcessor) c.newInstance();
            } catch (Exception e) {
                throw new Context4jException("Processor casting error");
            }
        }).collect(Collectors.toSet());
    }
}

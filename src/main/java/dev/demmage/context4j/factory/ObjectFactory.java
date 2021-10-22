package dev.demmage.context4j.factory;

import dev.demmage.context4j.exceptions.MultipleImplementationsException;
import dev.demmage.context4j.exceptions.NoSuchImplementationException;
import dev.demmage.context4j.exceptions.NotImplementedException;
import dev.demmage.context4j.scan.ReflectionsConfigurer;
import lombok.SneakyThrows;
import org.reflections.Reflections;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ObjectFactory {

    private final Reflections scanner = ReflectionsConfigurer.getReflections();
    private final Map<Class<?>, Class<?>> interface2ImplClass = new HashMap<>();

    @SneakyThrows
    public <T> T createComponent(Class<T> type, @Nullable Object... constructorParams) {
        Class<? extends T> implClass = type;
        if (type.isInterface()) {
            implClass = getImplClass(type, null);
        }

        return implClass.getDeclaredConstructor().newInstance(constructorParams);
    }

    public <T> Class<? extends T> getImplClass(Class<T> type, String qualifier) {
        return (Class<? extends T>) interface2ImplClass.computeIfAbsent(type, clazz -> {
            Set<Class<? extends T>> classes = scanner.getSubTypesOf(type);

            if (classes.isEmpty()) {
                throw new NotImplementedException("Class " + type + "not implemented");
            } else if (classes.size() != 1) {
                if (qualifier == null) {
                    throw new MultipleImplementationsException("Class " + type + "has more than one implementation");
                }

                return classes.stream()
                        .filter(c -> c.getSimpleName().equals(qualifier))
                        .findFirst().orElseThrow(NoSuchImplementationException::new);
            }

            return classes.iterator().next();
        });
    }
}

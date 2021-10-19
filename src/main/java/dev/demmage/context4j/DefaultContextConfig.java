package dev.demmage.context4j;

import dev.demmage.context4j.exceptions.MultipleImplementationsException;
import dev.demmage.context4j.exceptions.NoSuchImplementationException;
import dev.demmage.context4j.exceptions.NotImplementedException;
import org.reflections.Reflections;

import java.util.Map;
import java.util.Set;

public class DefaultContextConfig implements ContextConfig {

    private Reflections scanner;
    private Map<Class<?>, Class<?>> interface2ImplClass;

    public DefaultContextConfig(String packageToScan, Map<Class<?>, Class<?>> interface2ImplClass) {
        this.interface2ImplClass = interface2ImplClass;
        this.scanner = new Reflections(packageToScan);
    }

    @Override
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

package dev.demmage.context4j;

import lombok.SneakyThrows;

import java.lang.invoke.SerializedLambda;
import java.util.HashMap;

public class ObjectFactory {

    private static ObjectFactory instance = new ObjectFactory();
    private ContextConfig config;

    private ObjectFactory() {
        config = new DefaultContextConfig("dev.demmage.context4j.tests", new HashMap<>());
    }

    public static ObjectFactory getInstance() {
        return instance;
    }

    @SneakyThrows
    public <T> T createComponent(Class<T> type) {
        Class<? extends T> implClass = type;
        if (type.isInterface()) {
            implClass = config.getImplClass(type, null);
        }

        return implClass.getDeclaredConstructor().newInstance();
    }
}

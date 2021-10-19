package dev.demmage.context4j;

public interface ContextConfig {

    <T> Class<? extends T> getImplClass(Class<T> type, String qualifier);
}

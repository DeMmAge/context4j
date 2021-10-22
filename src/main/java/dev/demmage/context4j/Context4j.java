package dev.demmage.context4j;

import dev.demmage.context4j.config.ConfigurationParser;
import dev.demmage.context4j.config.Configuration;
import dev.demmage.context4j.exceptions.Context4jNotInitializedException;

import java.util.Collection;

public class Context4j {

    private static Context context;
    private static final Configuration configuration = new ConfigurationParser().parse();

    private Context4j() {

    }

    public static void init() {
        context = new Context();

        context.createComponents();
        context.executePreProcessors();
        context.executePostProcessors();
    }

    public static Configuration getConfiguration() {
        return configuration;
    }

    public static Object getComponent(String name) {
        validateContext();
        return context.getComponent(name);
    }

    public static Collection<Object> getComponents() {
        validateContext();
        return context.getComponents();
    }

    private static void validateContext() {
        if (context == null) {
            throw new Context4jNotInitializedException();
        }
    }
}

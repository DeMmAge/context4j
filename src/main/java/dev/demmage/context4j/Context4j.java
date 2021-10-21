package dev.demmage.context4j;

import dev.demmage.context4j.config.ConfigurationParser;
import dev.demmage.context4j.config.Context4jConfiguration;
import dev.demmage.context4j.exceptions.Context4jNotInitializedException;

import java.util.Collection;

public class Context4j {

    private static Context context;
    private static final Context4jConfiguration configuration = new ConfigurationParser().parse();

    private Context4j() {

    }

    public static void init() {
        context = new Context();

        context.executePreProcessors();
        context.createComponents();
        context.executePostProcessors();
    }

    public static Context4jConfiguration getConfiguration() {
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

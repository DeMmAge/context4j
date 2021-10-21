package dev.demmage.context4j.scan;

import dev.demmage.context4j.Context4j;
import dev.demmage.context4j.exceptions.Context4jException;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;

public abstract class ReflectionsConfigurer {

    private static final Reflections reflections;

    private ReflectionsConfigurer() {

    }

    static {
        String[] packages = Context4j.getConfiguration().getPackages();

        if (packages.length == 0) {
            String mainClass = null;
            StackTraceElement[] trace = Thread.currentThread().getStackTrace();
            if (trace.length > 0) {
                mainClass = trace[trace.length - 1].getClassName();
            }

            if (mainClass != null) {
                try {
                    reflections = new Reflections(new ConfigurationBuilder().forPackage(Class.forName(mainClass).getPackage().getName()));
                } catch (ClassNotFoundException e) {
                    throw new Context4jException();
                }

            } else {
                reflections = new Reflections();
            }

        } else {
            reflections = new Reflections(new ConfigurationBuilder().forPackages(packages));
        }
    }

    public static Reflections getReflections() {
        return reflections;
    }
}
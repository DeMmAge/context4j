package dev.demmage.context4j.scan;

import dev.demmage.context4j.Context4j;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;

public abstract class Scanner {

    protected Reflections reflections;

    protected Scanner() {
        String[] packages = Context4j.getConfiguration().getPackages();

        if (packages.length == 0) {
            reflections = new Reflections();
        } else {
            reflections = new Reflections(new ConfigurationBuilder().forPackages(packages));
        }
    }
}

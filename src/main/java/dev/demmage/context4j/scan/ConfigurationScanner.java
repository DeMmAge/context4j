package dev.demmage.context4j.scan;

import dev.demmage.context4j.config.Context4jConfiguration;
import dev.demmage.context4j.config.DefaultContext4jConfiguration;
import dev.demmage.context4j.exceptions.MultipleConfigurationsException;
import lombok.SneakyThrows;

import java.util.Set;
import java.util.stream.Collectors;

public class ConfigurationScanner extends Scanner {

    @SneakyThrows
    public Context4jConfiguration getConfiguration() {
        Set<Class<? extends Context4jConfiguration>> configs = reflections.getSubTypesOf(Context4jConfiguration.class).stream()
                .filter(c -> c == DefaultContext4jConfiguration.class).collect(Collectors.toSet());

        if (configs.isEmpty()) {
            return new DefaultContext4jConfiguration();
        } else if (configs.size() > 1) {
            throw new MultipleConfigurationsException();
        } else {
            return configs.iterator().next().newInstance();
        }
    }
}

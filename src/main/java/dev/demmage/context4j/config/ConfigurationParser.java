package dev.demmage.context4j.config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigurationParser {

    public Configuration parse() {
        DefaultConfiguration defaultConfig = new DefaultConfiguration();
        Properties properties = new Properties();

        try (InputStream stream = getClass().getClassLoader().getResourceAsStream("context4j.properties")) {
            properties.load(stream);
        } catch (Exception e) {
            return defaultConfig;
        }

        return new Configuration() {
            @Override
            public String[] getPackages() {
                return properties.getProperty("packages", "").split(",");
            }
        };
    }

}

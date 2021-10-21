package dev.demmage.context4j.config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigurationParser {

    public Context4jConfiguration parse() {
        DefaultContext4jConfiguration defaultConfig = new DefaultContext4jConfiguration();
        Properties properties = new Properties();

        try (InputStream stream = getClass().getClassLoader().getResourceAsStream("context4j.properties")) {
            properties.load(stream);
        } catch (Exception e) {
            return defaultConfig;
        }

        return new Context4jConfiguration() {
            @Override
            public String[] getPackages() {
                return properties.getProperty("packages", "").split(",");
            }
        };
    }

}

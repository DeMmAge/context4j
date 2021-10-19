package dev.demmage.context4j;

import java.util.Map;

class Context {

    private Map<String, Object> components;

    public Object getComponent(String name) {
        return components.get(name);
    }


}

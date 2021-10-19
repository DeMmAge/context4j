package dev.demmage.context4j;

public class Context4j {

    private static Context context;

    public static void init() {
        context = new Context();
    }

    public static Object getComponent(String name) {
        return context.getComponent(name);
    }
}

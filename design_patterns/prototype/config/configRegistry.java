package design_patterns.prototype.config;

import java.util.HashMap;

public class configRegistry implements ConfigurationPrototypeRegistry{

    private HashMap<String, Configuration> map = new HashMap<>();
    @Override
    public void addPrototype(Configuration user) {
        map.put(user.getType().toString(), user);
    }

    @Override
    public Configuration getPrototype(ConfigurationType type) {
        return map.get(type.toString());
    }

    @Override
    public Configuration clone(ConfigurationType type) {
        return map.get(type.toString()).cloneObject();
    }
}

package design_patterns.prototype.user;

import java.util.HashMap;

public class UserPrototypeRegistryImpl implements UserPrototypeRegistry {

    private HashMap<String, User> userHashMap = new HashMap<>();
    @Override
    public void addPrototype(User user) {
        userHashMap.put(user.getType().toString(), user);
    }

    @Override
    public User getPrototype(UserType type) {
        return userHashMap.get(type.toString());
    }

    @Override
    public User clone(UserType type) {
        return userHashMap.get(type.toString()).cloneObject();
    }
}

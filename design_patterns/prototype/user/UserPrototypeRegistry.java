package design_patterns.prototype.user;

public interface UserPrototypeRegistry {

    void addPrototype(User user);

    User getPrototype(UserType type);

    User clone(UserType type);
}

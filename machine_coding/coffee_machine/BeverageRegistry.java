package machine_coding.coffee_machine;

import java.util.HashMap;

public class BeverageRegistry {
    private HashMap<String, Beverage> beverageMap;
    private static BeverageRegistry beverageRegistry = null;

    private BeverageRegistry()
    {
        beverageMap = new HashMap<>();
    }

    public static BeverageRegistry getInstance()
    {
        if(beverageRegistry == null)
        {
            beverageRegistry = new BeverageRegistry();
        }
        return beverageRegistry;
    }

    public void addRegistry(String name, Beverage beverage)
    {
        beverageMap.put(name, beverage);
    }

    public Beverage getRegistry(String name)
    {
        return beverageMap.get(name);
    }

    public void removeRegistry(String name)
    {
        beverageMap.remove(name);
    }

}

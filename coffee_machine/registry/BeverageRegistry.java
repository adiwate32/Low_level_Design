package coffee_machine.registry;

import coffee_machine.services.Beverage;

import java.util.HashMap;

public class BeverageRegistry {
    HashMap<String, Beverage> beverageHashMap = new HashMap<>();

    private static BeverageRegistry beverageRegistry = null;

    private BeverageRegistry(){};

    public static BeverageRegistry getBeverageRegistry()
    {
        if(beverageRegistry == null)
        {
            return new BeverageRegistry();
        }
        return beverageRegistry;
    }

    public void addBeverage(String name, Beverage beverage)
    {
        beverageHashMap.put(name, beverage);
    }

    public Beverage getBeverage(String name)
    {
       return beverageHashMap.get(name);
    }
}

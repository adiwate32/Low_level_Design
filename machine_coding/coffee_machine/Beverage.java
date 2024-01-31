package machine_coding.coffee_machine;

import java.util.HashMap;

public class Beverage {
    private String name;

    private HashMap<String, Integer> ingredientsMap;

    public Beverage(String name)
    {
        this.name = name;
        this.ingredientsMap = new HashMap<>();
    }

    public void addIngredient(String name, int quantity)
    {
        this.ingredientsMap.put(name, quantity);
    }

    public String getName()
    {
        return this.name;
    }

    public HashMap<String, Integer> getIngredients() {
        return ingredientsMap;
    }
}

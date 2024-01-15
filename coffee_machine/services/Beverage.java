package coffee_machine.services;

import java.util.HashMap;

public class Beverage {
    private HashMap<String, Integer> ingredients;

    private Beverage(BeverageBuilder beverageBuilder)
    {
        this.ingredients = beverageBuilder.ingredients;
    }

    public static BeverageBuilder getBuilder()
    {
        return new BeverageBuilder();
    }

    public HashMap<String, Integer> getIngredients()
    {
        return this.ingredients;
    }

    public static class BeverageBuilder {

        private HashMap<String, Integer> ingredients = new HashMap<>();

        public BeverageBuilder addIngredient(String name, int quantity)
        {
            ingredients.put(name, quantity);
            return this;
        }

        public Beverage build() {
            return new Beverage(this);
        }
    }
}

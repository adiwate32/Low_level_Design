package machine_coding.coffee_machine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CoffeeMachine {
    private static CoffeeMachine coffeeMachine = null;

    private HashMap<String, Ingredient> ingredientsMap;

    private List<Outlet> outlets;

    private CoffeeMachine (){
        this.ingredientsMap = new HashMap<>();
        this.outlets = new ArrayList<>();
    }

    public static CoffeeMachine getInstance()
    {
        if(coffeeMachine != null)
        {
            coffeeMachine = new CoffeeMachine();
        }
        return coffeeMachine;
    }

    public void addOutlet(String name)
    {
        this.outlets.add(new Outlet(name));
    }

    public void addIngredient(String name, int threshold)
    {
        if(!ingredientsMap.containsKey(name))
        {
            ingredientsMap.put(name, new Ingredient(name, threshold));
        }
    }

    public void fillIngredient(String name, int quantity)
    {
        if(ingredientsMap.containsKey(name))
        {
            ingredientsMap.get(name).setQuantity(quantity);
        }
        else
        {
            throw new IllegalStateException(name + "ingredient not added to coffee machine");
        }
    }

    public void prepareBeverage(Beverage beverage)
    {
        System.out.println("Preparing beverage " + beverage.getName());

        try {
            HashMap<String, Integer> beverageIngredients = beverage.getIngredients();
            for (Map.Entry<String, Integer> entry : beverageIngredients.entrySet()) {
                int qty = entry.getValue();
                String ingredientName = entry.getKey();
                ingredientsMap.get(ingredientName).consumeIngredient(qty);
            }

            System.out.println("Successfully prepared beverage " + beverage.getName());

        } catch (Exception e) {
            throw new RuntimeException("Cannot prepare beverage because sufficient ingredients are not present");
        }
    }

    public void serveBeverage(Beverage beverage) throws InterruptedException {
        for(Outlet outlet: outlets)
        {
            if(outlet.getStatus() == OutletStatus.AVAILABLE)
            {
                System.out.println("Assigning beverage to outlet " + outlet.getName());
                prepareBeverage(beverage);
                outlet.serveBeverage(beverage.getName());
            }
        }
    }

    public void serveBeverages(List<String> beverageNames){
        ExecutorService executorService = Executors.newFixedThreadPool(outlets.size());
        BeverageRegistry beverageRegistry = BeverageRegistry.getInstance();

        for(String beverageName: beverageNames)
        {
            Beverage beverage = beverageRegistry.getRegistry(beverageName);
            executorService.submit(() -> {
                try {
                    serveBeverage(beverage);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
            });
        }
    }
}

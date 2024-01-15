package coffee_machine.services;

import coffee_machine.models.IngredientType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class BeverageMachine {
    private List<Outlet> outlets;

    private HashMap<String, Ingredient> ingredients;

    private HashMap<String, Beverage> beverages;

//    private BeverageRegistry beverageRegistry = BeverageRegistry.getBeverageRegistry();

    private BeverageMachine(BeverageMachineBuilder beverageMachineBuilder)
    {
        this.ingredients = beverageMachineBuilder.ingredients;
        this.beverages = beverageMachineBuilder.beverages;
        this.outlets = new ArrayList<>();
    }

    public static BeverageMachineBuilder getBeverageMachineBuilder()
    {
        return new BeverageMachineBuilder();
    }

    public boolean canServeBeverage(String BeverageName)
    {
        for(Map.Entry<String, Integer> entry: beverages.get(BeverageName).getIngredients().entrySet())
        {
            String ingredientName = entry.getKey();
            int reqQuantity = entry.getValue();

            int avlQuantity = this.ingredients.get(ingredientName).getQuantity();

            if(reqQuantity > avlQuantity)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * This method updates all the ingredients for a given beverage Name
     */
    public void updateIngredients(String BeverageName)
    {
        for(Map.Entry<String, Integer> entry: beverages.get(BeverageName).getIngredients().entrySet())
        {
            String ingredientName = entry.getKey();
            int reqQuantity = entry.getValue();

            this.ingredients.get(ingredientName).useIngredient(reqQuantity);
        }
    }

    /**
     * This Method on checking of available ingredients. It serves beverage
     */
    public String serveBeverage(String BeverageName)
    {
        if(this.canServeBeverage(BeverageName))
        {
            synchronized (this) {
            this.updateIngredients(BeverageName);
            }
            return "Successfully served beverage";
        }
        return "Not successfully served beverage";
    }

    public void ServeBeverages(List<String> BeverageNames) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(outlets.size());
        List<Future<String>> futures = new ArrayList<>();

        for(String name: BeverageNames)
        {
            for(Outlet outlet: outlets)
            {
                if(!outlet.isBusy())
                {
                    outlet.setBeverage(name);
                    futures.add(executor.submit(outlet));
                    break;
                }
            }
        }

        for(Future<String> future: futures)
        {
            try
            {
                System.out.println(future.get());
            }
            catch (Exception ex)
            {
                throw new RuntimeException(ex);
            }
        }
        executor.shutdown();
    }

    public List<Ingredient> getLowIngredients()
    {
        List<Ingredient> ingredientList = new ArrayList<>();
        for(Map.Entry<String, Ingredient> entry: this.ingredients.entrySet())
        {
            Ingredient ingredient = entry.getValue();

            if(ingredient.isLow())
            {
                ingredientList.add(ingredient);
            }
        }
        return ingredientList;
    }

    public void refillIngredient(int quantity, String name)
    {
        Ingredient ingredient = ingredients.get(name);
        ingredient.setQuantity(ingredient.getQuantity() + quantity);
    }

    public void addOutlet (String name)
    {
        outlets.add(new Outlet(name, this));
    }

    public static class BeverageMachineBuilder
    {
        private List <String> outletNames;

        private HashMap<String, Ingredient> ingredients;

        private HashMap<String, Beverage> beverages;

        private BeverageMachineBuilder()
        {
            outletNames = new ArrayList<>();
            ingredients = new HashMap<>();
            beverages = new HashMap<>();
        }

        public BeverageMachineBuilder addIngredient(String name, IngredientType ingredientType, int minThreshold, int quantity)
        {
            ingredients.put(name, new Ingredient(name, ingredientType, minThreshold).setQuantity(quantity));
            return this;
        }

        public BeverageMachineBuilder addBeverage(String name, Beverage beverage)
        {
            beverages.put(name, beverage);
            return this;
        }

        public BeverageMachineBuilder addOutlet (String name)
        {
            outletNames.add(name);
            return this;
        }

        public BeverageMachine build()
        {
            BeverageMachine beverageMachine = new BeverageMachine(this);

            for(String outletName: this.outletNames)
            {
                beverageMachine.addOutlet(outletName);
            }

            return beverageMachine;
        }
    }
}

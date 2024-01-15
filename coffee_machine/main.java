package coffee_machine;

import coffee_machine.models.IngredientType;
import coffee_machine.registry.BeverageRegistry;
import coffee_machine.services.Beverage;
import coffee_machine.services.BeverageMachine;

import java.util.Arrays;

public class main {
    public static void main(String[] args) throws Exception {
        System.out.println("Starting application");

//        BeverageRegistry beverageRegistry = BeverageRegistry.getBeverageRegistry();

        Beverage coffee = Beverage.getBuilder().addIngredient("Coffee Beans", 250)
                .addIngredient("Water", 200)
                .addIngredient("Milk", 200)
                .addIngredient("Sugar", 200)
                .build();

        Beverage tea = Beverage.getBuilder().addIngredient("Tea Leaves", 250)
                .addIngredient("Water", 200)
                .addIngredient("Milk", 200)
                .addIngredient("Sugar", 200)
                .build();

        Beverage hot_chocolate = Beverage.getBuilder()
                .addIngredient("Water", 200)
                .addIngredient("Milk", 200)
                .addIngredient("Sugar", 200)
                .build();

        BeverageMachine beverageMachine = BeverageMachine.getBeverageMachineBuilder().addOutlet("Outlet1")
                .addOutlet("Outlet2")
                .addIngredient("Water", IngredientType.Liquid, 100, 800)
                .addIngredient("Milk", IngredientType.Liquid, 100, 900)
                .addIngredient("Sugar", IngredientType.Solid, 100, 600)
                .addIngredient("Tea Leaves", IngredientType.Solid, 100, 500)
                .addIngredient("Coffee Beans", IngredientType.Solid, 100, 600)
                .addBeverage("Coffee", coffee)
                .addBeverage("Tea", tea)
                .addBeverage("Hot Chocolate", hot_chocolate).build();

        beverageMachine.ServeBeverages(Arrays.asList("Coffee", "Tea", "Hot Chocolate"));

        System.out.println("Successful");
    }
}

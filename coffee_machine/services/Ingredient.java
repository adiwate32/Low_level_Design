package coffee_machine.services;

import coffee_machine.models.IngredientType;

public class Ingredient {
    private int avlQuantity;

    private String name;

    private int minThreshold;

    IngredientType  ingredientType;

    public Ingredient(String name, IngredientType ingredientType, int minThreshold)
    {
        this.name = name;
        this.ingredientType = ingredientType;
        this.minThreshold = minThreshold;
    }

    public int getQuantity()
    {
        return this.avlQuantity;
    }

    public Ingredient setQuantity(int quantity)
    {
       this.avlQuantity =  quantity;
       return this;
    }

    public void useIngredient(int quantity)
    {
        this.avlQuantity -= quantity;
    }

   public void refill(int quantity)
   {
      this.avlQuantity += quantity;
   }

   public boolean isLow()
   {
       return this.avlQuantity <= this.minThreshold;
   }
}

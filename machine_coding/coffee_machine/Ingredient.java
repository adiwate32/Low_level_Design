package machine_coding.coffee_machine;

public class Ingredient {
    private String name;
    private int quantity;
    private int threshold;

    public Ingredient(String name, int threshold)
    {
        this.name = name;
        this.quantity = 0;
    }

    public void consumeIngredient(int quantity) throws Exception {
        if(checkIngredient(quantity))
        {
            this.quantity -= quantity;
        }
        else {
            throw new Exception("We Don't have sufficient Ingredient");
        }
    }

    public void setQuantity(int quantity) {
        this.quantity += quantity;
    }

    public boolean checkIngredient(int quantity)
    {
        return quantity <= this.quantity;
    }
}

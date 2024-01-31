# Coffee Machine

## Requirement

1. It will be serving some **beverages.**
2. Each beverage will be made using some **ingredients.**
3. Assume time to prepare a beverage is the same for all cases.
4. The quantity of ingredients used for each beverage can vary. Also, the same ingredient (ex:water) can be used for multiple beverages.
5. There would be N ( N is an integer ) outlet from which beverages can be served.
6. Maximum N beverages can be served in parallel.
7. Any beverage can be served only if all the ingredients are available in terms of quantity.
8. There would be an indicator that would show which all ingredients are running low. We need some methods to refill them.

## List of Beverages and Ingredients
Coffee:
Coffee beans
Water
Milk
Sugar

Hot Chocolate:
Cocoa powder`
Milk
Sugar

class Diagram

1. Ingredient:
   attr:
   - name: str 
   - quantity: int 
   - threshold: int
   methods:
   + void setQuantity(quantity: int)
   + void setThreshold(threshold: int)
   + boolean isLow()
   + boolean consumeIngredient(quantity: int)

   IngredientType - ENUM
   + LIQUID
   + SOLID

2. Beverage
   abstract Beverage
   attr:
   - name: str
   - ingredientMap: HashMap<String, Integer>
   - price: int
   methods:
   + abstract void setIngredient(name: str, quantity: int)
   + abstract void setPrice(price: int)
   + HashMap<String, Integer> getIngredients()

   MedBeverage  | SmallBeverage
   methods:
   + void setIngredient(name: str, quantity: int)
   + void setPrice(price: int)
   
   BeverageType - ENUM
   + SMALL
   + MEDIUM

3. Outlet
   attr:
   - name: str
   methods:
   + void serve(Beverage beverage)
   + OutletStatus status()

   OutletStatus - ENUM
   + BUSY
   + AVAILABLE

4. CoffeeMachine
   attr:
   - name: str
   - ingredients: HashMap<String, Ingredient>
   - outlets: List<Outlet>
   methods:
   - void addIngredient(name: str, quantity: int)
   - void addOutlet(name: str)
   - void prepareBeverage(beverage: Beverage)
   - List<Ingedients> getLowIngredients()
   - void refill(name:str, quantity: int)

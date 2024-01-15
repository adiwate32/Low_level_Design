# Coffee Machine

## Requirement

1. It will be serving some beverages.
2. Each beverage will be made using some ingredients.
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

Tea:
Tea leaves
Water
Milk
Sugar

Hot Chocolate:
Cocoa powder
Milk
Sugar

## Class Diagram

Beverage
1. Map<Ingredient, Quantity(int)>
2. CanServe()
3. PrepareBreverage()

Outlet
1. Current Beverage
2. serving -> bool
3. serveBeverage


Ingredient
1. Available quantity
2. useIngredient(amount)
3. refill(amount)
4. trigger()

BeverageMachine
1. List<Outlets>
2. avlOutlets
3. List<Ingredients>
4. getLowIngredients()
5. serveBreverage
6. refillIngredients()


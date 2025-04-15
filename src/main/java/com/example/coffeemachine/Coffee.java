package com.example.coffeemachine;

import java.time.LocalDateTime;

public class Coffee {
    private final DrinkType type;
    private final Recipe recipe;
    private final LocalDateTime timeMade;
    private boolean consumed;
    
    public Coffee(DrinkType type, Recipe recipe) {
        this.type = type;
        this.recipe = recipe;
        this.timeMade = LocalDateTime.now();
        this.consumed = false;
    }
    
    public void drink() {
        if (!consumed) {
            System.out.println("Drinking " + type + "... Delicious!");
            consumed = true;
        } else {
            System.out.println("This coffee has already been consumed!");
        }
    }
    
    public DrinkType getType() {
        return type;
    }
    
    public Recipe getRecipe() {
        return recipe;
    }
    
    public LocalDateTime getTimeMade() {
        return timeMade;
    }
    
    public boolean isConsumed() {
        return consumed;
    }
    
    @Override
    public String toString() {
        return type + " coffee made at " + timeMade + 
               (consumed ? " (consumed)" : " (not consumed yet)");
    }
}
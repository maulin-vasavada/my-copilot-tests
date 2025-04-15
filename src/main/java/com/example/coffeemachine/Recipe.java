package com.example.coffeemachine;

public class Recipe {
    private final int waterAmount;  // in ml
    private final int beanAmount;   // in grams
    private final int milkAmount;   // in ml
    
    public Recipe(int waterAmount, int beanAmount, int milkAmount) {
        this.waterAmount = waterAmount;
        this.beanAmount = beanAmount;
        this.milkAmount = milkAmount;
    }
    
    public int getWaterAmount() {
        return waterAmount;
    }
    
    public int getBeanAmount() {
        return beanAmount;
    }
    
    public int getMilkAmount() {
        return milkAmount;
    }
    
    @Override
    public String toString() {
        return "Recipe{" +
                "water=" + waterAmount + "ml, " +
                "beans=" + beanAmount + "g, " +
                "milk=" + milkAmount + "ml" +
                '}';
    }
}
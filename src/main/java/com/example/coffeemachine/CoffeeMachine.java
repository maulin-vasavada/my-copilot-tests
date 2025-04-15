package com.example.coffeemachine;

import java.util.HashMap;
import java.util.Map;

public class CoffeeMachine {
    private WaterTank waterTank;
    private BeanContainer beanContainer;
    private MilkContainer milkContainer;
    private Map<DrinkType, Recipe> recipes;
    private boolean isOn;
    private int coffeesServed;

    public CoffeeMachine(int waterCapacity, int beanCapacity, int milkCapacity) {
        this.waterTank = new WaterTank(waterCapacity);
        this.beanContainer = new BeanContainer(beanCapacity);
        this.milkContainer = new MilkContainer(milkCapacity);
        this.isOn = false;
        this.coffeesServed = 0;
        initializeRecipes();
    }

    private void initializeRecipes() {
        this.recipes = new HashMap<>();
        
        // Add default recipes
        recipes.put(DrinkType.ESPRESSO, new Recipe(30, 8, 0));
        recipes.put(DrinkType.AMERICANO, new Recipe(100, 8, 0));
        recipes.put(DrinkType.LATTE, new Recipe(30, 8, 70));
        recipes.put(DrinkType.CAPPUCCINO, new Recipe(30, 8, 40));
        recipes.put(DrinkType.FLAT_WHITE, new Recipe(30, 10, 50));
    }
    
    public void turnOn() {
        System.out.println("Coffee machine is turning on...");
        this.isOn = true;
        System.out.println("Coffee machine is ready to use!");
    }
    
    public void turnOff() {
        System.out.println("Coffee machine is turning off...");
        this.isOn = false;
        System.out.println("Coffee machine is now off.");
    }
    
    public void refillWater() {
        waterTank.refill();
        System.out.println("Water tank has been refilled to maximum capacity.");
    }
    
    public void refillBeans() {
        beanContainer.refill();
        System.out.println("Bean container has been refilled to maximum capacity.");
    }
    
    public void refillMilk() {
        milkContainer.refill();
        System.out.println("Milk container has been refilled to maximum capacity.");
    }
    
    public Coffee makeCoffee(DrinkType drinkType) {
        if (!isOn) {
            System.out.println("Cannot make coffee: Machine is off");
            return null;
        }
        
        Recipe recipe = recipes.get(drinkType);
        if (recipe == null) {
            System.out.println("Unknown coffee type: " + drinkType);
            return null;
        }
        
        // Check resources
        if (!hasSufficientResources(recipe)) {
            System.out.println("Insufficient resources to make " + drinkType);
            return null;
        }
        
        // Use resources
        System.out.println("Making " + drinkType + "...");
        waterTank.useWater(recipe.getWaterAmount());
        beanContainer.useBeans(recipe.getBeanAmount());
        milkContainer.useMilk(recipe.getMilkAmount());
        
        coffeesServed++;
        
        System.out.println(drinkType + " is ready!");
        return new Coffee(drinkType, recipe);
    }
    
    private boolean hasSufficientResources(Recipe recipe) {
        if (waterTank.getCurrentLevel() < recipe.getWaterAmount()) {
            System.out.println("Not enough water!");
            return false;
        }
        
        if (beanContainer.getCurrentLevel() < recipe.getBeanAmount()) {
            System.out.println("Not enough coffee beans!");
            return false;
        }
        
        if (milkContainer.getCurrentLevel() < recipe.getMilkAmount()) {
            System.out.println("Not enough milk!");
            return false;
        }
        
        return true;
    }
    
    public void displayStatus() {
        System.out.println("===== COFFEE MACHINE STATUS =====");
        System.out.println("Power: " + (isOn ? "ON" : "OFF"));
        System.out.println("Water level: " + waterTank.getCurrentLevel() + "/" + waterTank.getCapacity() + " ml");
        System.out.println("Bean level: " + beanContainer.getCurrentLevel() + "/" + beanContainer.getCapacity() + " g");
        System.out.println("Milk level: " + milkContainer.getCurrentLevel() + "/" + milkContainer.getCapacity() + " ml");
        System.out.println("Coffees served: " + coffeesServed);
        System.out.println("==============================");
    }
    
    public boolean isOn() {
        return isOn;
    }
    
    public int getCoffeesServed() {
        return coffeesServed;
    }
}
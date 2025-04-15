package com.example.coffeemachine;

public class MilkContainer extends Container {
    
    public MilkContainer(int capacity) {
        super(capacity);
    }
    
    public boolean useMilk(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Milk amount cannot be negative");
        }
        
        if (currentLevel < amount) {
            return false;
        }
        
        currentLevel -= amount;
        return true;
    }
}
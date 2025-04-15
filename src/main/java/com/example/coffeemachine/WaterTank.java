package com.example.coffeemachine;

public class WaterTank extends Container {
    
    public WaterTank(int capacity) {
        super(capacity);
    }
    
    public boolean useWater(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Water amount must be positive");
        }
        
        if (currentLevel < amount) {
            return false;
        }
        
        currentLevel -= amount;
        return true;
    }
}
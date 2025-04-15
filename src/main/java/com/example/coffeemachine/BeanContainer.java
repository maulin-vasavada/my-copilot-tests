package com.example.coffeemachine;

public class BeanContainer extends Container {
    
    public BeanContainer(int capacity) {
        super(capacity);
    }
    
    public boolean useBeans(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Bean amount must be positive");
        }
        
        if (currentLevel < amount) {
            return false;
        }
        
        currentLevel -= amount;
        return true;
    }
}
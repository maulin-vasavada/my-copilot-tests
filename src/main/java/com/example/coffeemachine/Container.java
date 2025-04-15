package com.example.coffeemachine;

public abstract class Container {
    protected final int capacity;
    protected int currentLevel;
    
    public Container(int capacity) {
        this.capacity = capacity;
        this.currentLevel = 0;
    }
    
    public void refill() {
        this.currentLevel = this.capacity;
    }
    
    public int getCapacity() {
        return capacity;
    }
    
    public int getCurrentLevel() {
        return currentLevel;
    }
    
    @Override
    public String toString() {
        return "Container{" +
                "currentLevel=" + currentLevel +
                "/" + capacity +
                '}';
    }
}
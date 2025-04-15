package com.example.coffeemachine;

public class CoffeeMachineDemo {
    public static void main(String[] args) {
        // Create a new coffee machine with specified capacities
        CoffeeMachine coffeeMachine = new CoffeeMachine(1000, 200, 500);
        
        // Display initial status
        System.out.println("Initial status:");
        coffeeMachine.displayStatus();
        System.out.println();
        
        // Fill the containers
        System.out.println("Filling containers...");
        coffeeMachine.refillWater();
        coffeeMachine.refillBeans();
        coffeeMachine.refillMilk();
        coffeeMachine.displayStatus();
        System.out.println();
        
        // Turn on the machine
        coffeeMachine.turnOn();
        System.out.println();
        
        // Make different types of coffee
        System.out.println("===== Making coffees =====");
        Coffee espresso = coffeeMachine.makeCoffee(DrinkType.ESPRESSO);
        Coffee latte = coffeeMachine.makeCoffee(DrinkType.LATTE);
        Coffee cappuccino = coffeeMachine.makeCoffee(DrinkType.CAPPUCCINO);
        System.out.println();
        
        // Display status after making coffees
        System.out.println("Status after making coffees:");
        coffeeMachine.displayStatus();
        System.out.println();
        
        // Drink the coffees
        System.out.println("===== Drinking coffees =====");
        if (espresso != null) {
            System.out.println("Espresso details: " + espresso);
            espresso.drink();
        }
        
        if (latte != null) {
            System.out.println("Latte details: " + latte);
            latte.drink();
        }
        
        if (cappuccino != null) {
            System.out.println("Cappuccino details: " + cappuccino);
            cappuccino.drink();
        }
        System.out.println();
        
        // Try to make coffee when resources are low (after many drinks)
        System.out.println("===== Testing resource limits =====");
        // Make several more coffees to deplete resources
        for (int i = 0; i < 10; i++) {
            Coffee coffee = coffeeMachine.makeCoffee(DrinkType.LATTE);
            if (coffee == null) {
                break;
            }
        }
        
        // Display final status
        System.out.println("\nFinal status:");
        coffeeMachine.displayStatus();
        
        // Turn off the machine
        System.out.println("\nShutting down...");
        coffeeMachine.turnOff();
    }
}
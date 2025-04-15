## Coffee Machine Simulation Overview

The simulation includes:

1. **Core Classes**:
   - `CoffeeMachine`: The main class that manages the coffee-making process
   - `DrinkType`: Enum defining different coffee types (ESPRESSO, AMERICANO, LATTE, etc.)
   - `Recipe`: Defines the ingredient amounts for each drink type
   - `Coffee`: Represents a prepared coffee drink

2. **Resource Management**:
   - `Container`: Abstract base class for resource containers
   - `WaterTank`, `BeanContainer`, and `MilkContainer`: Handle specific resources
   
3. **Demo Application**:
   - `CoffeeMachineDemo`: Showcases the functionality of the coffee machine

## Simulation Results

When running the simulation, the output showed:

1. **Initialization**: The coffee machine starts with empty containers and is turned off
2. **Resource Management**: We can refill water, beans, and milk containers
3. **Coffee Production**: Successfully made different types of coffee (ESPRESSO, LATTE, CAPPUCCINO)
4. **Status Tracking**: The machine tracks resources used and coffees served
5. **Resource Depletion**: After making several coffees, the milk ran out first, preventing further LATTE production

## Key Features Demonstrated

- **Object-Oriented Design**: Used inheritance, encapsulation, and polymorphism
- **Resource Management**: Tracked and consumed resources appropriately
- **Status Reporting**: Provided detailed status of the coffee machine
- **Error Handling**: Prevented coffee making when resources are insufficient
- **Extensibility**: Easy to add new drink types or change recipes

The simulation demonstrates a realistic coffee machine that manages resources, creates different types of coffee based on recipes, and tracks its status. The design is modular and can be easily extended to add features such as user accounts, payment processing, or maintenance schedules.
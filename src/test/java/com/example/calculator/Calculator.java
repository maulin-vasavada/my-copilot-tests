package com.example.calculator;

import java.util.HashMap;
import java.util.Map;

public class Calculator {

    private final Map<String, Operation> operations = new HashMap<>();

    public void registerOperation(String name, Operation operation) {
        operations.put(name, operation);
    }

    public double calculate(String operationName, double a, double b) {
        Operation operation = operations.get(operationName);
        if (operation == null) {
            throw new IllegalArgumentException("Operation not found: " + operationName);
        }
        return operation.apply(a, b);
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        // Register basic operations
        calculator.registerOperation("add", (a, b) -> a + b);
        calculator.registerOperation("subtract", (a, b) -> a - b);
        calculator.registerOperation("multiply", (a, b) -> a * b);
        calculator.registerOperation("divide", (a, b) -> {
            if (b == 0) throw new ArithmeticException("Division by zero");
            return a / b;
        });

        // Example usage
        System.out.println("Addition: " + calculator.calculate("add", 5, 3));
        System.out.println("Subtraction: " + calculator.calculate("subtract", 5, 3));
        System.out.println("Multiplication: " + calculator.calculate("multiply", 5, 3));
        System.out.println("Division: " + calculator.calculate("divide", 5, 3));
    }
}
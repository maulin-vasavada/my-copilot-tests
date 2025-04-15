package com.example.calculator;

@FunctionalInterface
public interface Operation {
    double apply(double a, double b);
}
package com.example.parkinglot;

public class Vehicle {
    private String licensePlate;
    private VehicleType type;
    
    public Vehicle(String licensePlate, VehicleType type) {
        this.licensePlate = licensePlate;
        this.type = type;
    }
    
    public String getLicensePlate() {
        return licensePlate;
    }
    
    public VehicleType getType() {
        return type;
    }
}

enum VehicleType {
    MOTORCYCLE,
    CAR,
    TRUCK,
    HANDICAPPED
}
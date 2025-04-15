package com.example.parkinglot;

public class ParkingSpot {
    private String id;
    private boolean isAvailable;
    private ParkingSpotType type;
    private Vehicle vehicle;
    private int floor;
    
    public ParkingSpot(String id, ParkingSpotType type, int floor) {
        this.id = id;
        this.type = type;
        this.floor = floor;
        this.isAvailable = true;
    }
    
    public boolean isAvailable() {
        return isAvailable;
    }
    
    public void occupy(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.isAvailable = false;
    }
    
    public void release() {
        this.vehicle = null;
        this.isAvailable = true;
    }
    
    public String getId() {
        return id;
    }
    
    public ParkingSpotType getType() {
        return type;
    }
    
    public int getFloor() {
        return floor;
    }
    
    public Vehicle getVehicle() {
        return vehicle;
    }
}

enum ParkingSpotType {
    MOTORCYCLE,
    REGULAR,
    LARGE,
    HANDICAPPED
}
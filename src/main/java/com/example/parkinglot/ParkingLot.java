package com.example.parkinglot;

import java.util.*;

public class ParkingLot {
    private String name;
    private Map<ParkingSpotType, List<ParkingSpot>> spots;
    private Map<String, Ticket> activeTickets;
    private PricingStrategy pricingStrategy;
    
    public ParkingLot(String name, PricingStrategy pricingStrategy) {
        this.name = name;
        this.spots = new HashMap<>();
        this.activeTickets = new HashMap<>();
        this.pricingStrategy = pricingStrategy;
        
        // Initialize parking spot collections
        for (ParkingSpotType type : ParkingSpotType.values()) {
            spots.put(type, new ArrayList<>());
        }
    }
    
    public void addParkingSpot(ParkingSpot spot) {
        spots.get(spot.getType()).add(spot);
    }
    
    public Ticket parkVehicle(Vehicle vehicle) {
        ParkingSpotType requiredType = getSpotTypeForVehicle(vehicle);
        ParkingSpot spot = findAvailableSpot(requiredType);
        
        if (spot == null) {
            throw new RuntimeException("No available parking spots for vehicle type: " + vehicle.getType());
        }
        
        spot.occupy(vehicle);
        Ticket ticket = new Ticket(vehicle, spot);
        activeTickets.put(ticket.getId(), ticket);
        return ticket;
    }
    
    public Receipt unparkVehicle(String ticketId) {
        Ticket ticket = activeTickets.get(ticketId);
        if (ticket == null) {
            throw new RuntimeException("Invalid ticket ID");
        }
        
        // Release the spot
        ticket.getParkingSpot().release();
        activeTickets.remove(ticketId);
        
        // Calculate fee
        double fee = pricingStrategy.calculateFee(ticket);
        return new Receipt(ticket, fee);
    }
    
    private ParkingSpot findAvailableSpot(ParkingSpotType type) {
        List<ParkingSpot> spotsForType = spots.get(type);
        for (ParkingSpot spot : spotsForType) {
            if (spot.isAvailable()) {
                return spot;
            }
        }
        return null;
    }
    
    private ParkingSpotType getSpotTypeForVehicle(Vehicle vehicle) {
        switch (vehicle.getType()) {
            case MOTORCYCLE:
                return ParkingSpotType.MOTORCYCLE;
            case CAR:
                return ParkingSpotType.REGULAR;
            case TRUCK:
                return ParkingSpotType.LARGE;
            case HANDICAPPED:
                return ParkingSpotType.HANDICAPPED;
            default:
                return ParkingSpotType.REGULAR;
        }
    }
    
    public String getName() {
        return name;
    }
    
    public int getAvailableSpots(ParkingSpotType type) {
        int count = 0;
        for (ParkingSpot spot : spots.get(type)) {
            if (spot.isAvailable()) {
                count++;
            }
        }
        return count;
    }
}
package com.example.parkinglot;

public class ParkingLotDemo {
    public static void main(String[] args) {
        // Create pricing strategy
        PricingStrategy pricingStrategy = new HourlyPricingStrategy();
        
        // Create parking lot
        ParkingLot parkingLot = new ParkingLot("Downtown Parking", pricingStrategy);
        
        // Add parking spots
        for (int i = 1; i <= 10; i++) {
            parkingLot.addParkingSpot(new ParkingSpot("M" + i, ParkingSpotType.MOTORCYCLE, 1));
        }
        
        for (int i = 1; i <= 50; i++) {
            parkingLot.addParkingSpot(new ParkingSpot("C" + i, ParkingSpotType.REGULAR, 1));
        }
        
        for (int i = 1; i <= 10; i++) {
            parkingLot.addParkingSpot(new ParkingSpot("L" + i, ParkingSpotType.LARGE, 1));
        }
        
        for (int i = 1; i <= 5; i++) {
            parkingLot.addParkingSpot(new ParkingSpot("H" + i, ParkingSpotType.HANDICAPPED, 1));
        }
        
        try {
            // Example usage
            System.out.println("Welcome to " + parkingLot.getName());
            
            // Park a car
            Vehicle car1 = new Vehicle("ABC123", VehicleType.CAR);
            Ticket ticket1 = parkingLot.parkVehicle(car1);
            System.out.println("Car parked with ticket: " + ticket1.getId());
            
            // Park a motorcycle
            Vehicle motorcycle1 = new Vehicle("XYZ789", VehicleType.MOTORCYCLE);
            Ticket ticket2 = parkingLot.parkVehicle(motorcycle1);
            System.out.println("Motorcycle parked with ticket: " + ticket2.getId());
            
            // Display available spots
            System.out.println("Available car spots: " + parkingLot.getAvailableSpots(ParkingSpotType.REGULAR));
            System.out.println("Available motorcycle spots: " + parkingLot.getAvailableSpots(ParkingSpotType.MOTORCYCLE));
            
            // Simulate some time passing (in a real application, this would be actual elapsed time)
            // For demo purposes, we'll just unpark the vehicle right away
            
            // Unpark the car
            Receipt receipt1 = parkingLot.unparkVehicle(ticket1.getId());
            System.out.println("\nCar unparked:");
            System.out.println(receipt1.printReceipt());
            
            // Unpark the motorcycle
            Receipt receipt2 = parkingLot.unparkVehicle(ticket2.getId());
            System.out.println("\nMotorcycle unparked:");
            System.out.println(receipt2.printReceipt());
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
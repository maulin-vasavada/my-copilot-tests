package com.example.parkinglot;

import java.time.Duration;
import java.time.LocalDateTime;

public interface PricingStrategy {
    double calculateFee(Ticket ticket);
}

class HourlyPricingStrategy implements PricingStrategy {
    private static final double MOTORCYCLE_RATE = 1.0;
    private static final double CAR_RATE = 2.0;
    private static final double TRUCK_RATE = 3.0;
    
    @Override
    public double calculateFee(Ticket ticket) {
        LocalDateTime entryTime = ticket.getEntryTime();
        LocalDateTime exitTime = LocalDateTime.now();
        
        Duration duration = Duration.between(entryTime, exitTime);
        long hours = duration.toHours() + (duration.toMinutesPart() > 0 ? 1 : 0); // Round up
        
        double hourlyRate;
        switch (ticket.getVehicle().getType()) {
            case MOTORCYCLE:
                hourlyRate = MOTORCYCLE_RATE;
                break;
            case TRUCK:
                hourlyRate = TRUCK_RATE;
                break;
            case CAR:
            case HANDICAPPED:
            default:
                hourlyRate = CAR_RATE;
                break;
        }
        
        return hours * hourlyRate;
    }
}
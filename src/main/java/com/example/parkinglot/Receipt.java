package com.example.parkinglot;

import java.time.LocalDateTime;

public class Receipt {
    private String id;
    private Ticket ticket;
    private LocalDateTime exitTime;
    private double fee;
    
    public Receipt(Ticket ticket, double fee) {
        this.id = ticket.getId();
        this.ticket = ticket;
        this.exitTime = LocalDateTime.now();
        this.fee = fee;
    }
    
    public String getId() {
        return id;
    }
    
    public Ticket getTicket() {
        return ticket;
    }
    
    public LocalDateTime getExitTime() {
        return exitTime;
    }
    
    public double getFee() {
        return fee;
    }
    
    public String printReceipt() {
        return "Receipt ID: " + id +
               "\nVehicle: " + ticket.getVehicle().getLicensePlate() +
               "\nEntry Time: " + ticket.getEntryTime() +
               "\nExit Time: " + exitTime +
               "\nFee: $" + String.format("%.2f", fee);
    }
}
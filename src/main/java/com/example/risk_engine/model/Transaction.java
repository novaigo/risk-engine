package com.example.risk_engine.model;

import java.time.LocalDateTime;

public class Transaction {
    private double amount;
    private int velocity;
    private LocalDateTime timestamp;
    private String timezone;
    private String country;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    //todo:
    public int getTransactionsInLastMinute() {
        // random number for simulation
        return (int)(Math.random() * 10); // 0 - 9 transactions
    }
}

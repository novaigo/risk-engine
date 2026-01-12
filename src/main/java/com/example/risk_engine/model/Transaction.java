package com.example.risk_engine.model;

public class Transaction {
    private double amount;
    private int velocity;
    private int hour;
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

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getTransactionsInLastMinute() {
        // random number for simulation
        return (int)(Math.random() * 10); // 0 - 9 transactions
    }
}

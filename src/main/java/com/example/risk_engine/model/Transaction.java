package com.example.risk_engine.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Transaction {
    private BigDecimal amount;
    private int velocity;
    private LocalDateTime timestamp;
    private String timezone;
    private String country;

    //todo:
    public int getTransactionsInLastMinute() {
        // random number for simulation
        return (int)(Math.random() * 10); // 0 - 9 transactions
    }
}

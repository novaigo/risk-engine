package com.example.risk_engine.model;

/**
 * Severity levels for rules.
 * Determines how a triggered rule contributes to the decision.
 */
public enum Severity {
    INFO(1.0),
    REVIEW(1.5),
    BLOCK(2.0);

    private final double weight;

    Severity(double weight) {
        this.weight = weight;
    }

    public double weight(){
        return weight;
    }
}

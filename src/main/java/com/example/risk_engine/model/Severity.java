package com.example.risk_engine.model;

/**
 * Severity levels for rules.
 * Determines how a triggered rule contributes to the decision.
 */
public enum Severity {
    INFO,
    REVIEW,
    BLOCK
}

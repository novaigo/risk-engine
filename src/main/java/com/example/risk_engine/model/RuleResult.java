package com.example.risk_engine.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Result of a single rule evaluation.
 * Represents how and why a rule contributed to the final decision.
 */
@Data
@RequiredArgsConstructor
public class RuleResult {

    private final String ruleName;
    private final boolean triggered;
    private final int score;
    private final String explanation;
    private final Severity severity;

    public int getEffectiveScore() {
       return (int) (score * severity.weight());
    }
}

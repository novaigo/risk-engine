package com.example.risk_engine.rules.config;

import com.example.risk_engine.model.Severity;

public class AmountRuleConfig implements RuleConfig {

    private final int maxAmount;
    private final int score;
    private final Severity severity;

    public AmountRuleConfig(int maxAmount, int score, Severity severity) {
        this.maxAmount = maxAmount;
        this.score = score;
        this.severity = severity;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public int getScore() {
        return score;
    }

    public Severity getSeverity() {
        return severity;
    }
}

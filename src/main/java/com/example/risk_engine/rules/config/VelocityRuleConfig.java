package com.example.risk_engine.rules.config;

import com.example.risk_engine.model.Severity;

public class VelocityRuleConfig implements RuleConfig {

    private final int maxTxPerMinute;
    private final int score;
    private final Severity severity;

    public VelocityRuleConfig(int maxTxPerMinute, int score, Severity severity) {
        this.maxTxPerMinute = maxTxPerMinute;
        this.score = score;
        this.severity = severity;
    }

    public int getMaxTxPerMinute() {
        return maxTxPerMinute;
    }

    public int getScore() {
        return score;
    }

    public Severity getSeverity() {
        return severity;
    }
}

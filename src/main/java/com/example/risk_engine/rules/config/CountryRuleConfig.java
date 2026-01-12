package com.example.risk_engine.rules.config;

import com.example.risk_engine.model.Severity;

import java.util.List;

public class CountryRuleConfig {

    private final List<String> highRiskCountries;
    private final int score;
    private final Severity severity;

    public CountryRuleConfig(List<String> highRiskCountries, int score, Severity severity) {
        this.highRiskCountries = highRiskCountries;
        this.score = score;
        this.severity = severity;
    }

    public List<String> getHighRiskCountries() {
        return highRiskCountries;
    }

    public int getScore() {
        return score;
    }

    public Severity getSeverity() {
        return severity;
    }
}

package com.example.risk_engine.rules.config;

import com.example.risk_engine.model.Severity;

public record HourRuleConfig(
        int startHour,
        int endHour,
        int score,
        Severity severity
) implements RuleConfig {}

package com.example.risk_engine.rules.config;

import com.example.risk_engine.model.Severity;

public record AmountRuleConfig(
        int maxAmount,
        int score,
        Severity severity
) implements RuleConfig {}

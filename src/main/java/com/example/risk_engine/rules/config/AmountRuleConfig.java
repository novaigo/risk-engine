package com.example.risk_engine.rules.config;

import com.example.risk_engine.model.Severity;
import java.math.BigDecimal;

public record AmountRuleConfig(
        BigDecimal maxAmount,
        int score,
        Severity severity
) implements RuleConfig {}

package com.example.risk_engine.rules.config;

import com.example.risk_engine.model.Severity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class VelocityRuleConfig implements RuleConfig {

    private final int maxTxPerMinute;
    private final int score;
    private final Severity severity;
}

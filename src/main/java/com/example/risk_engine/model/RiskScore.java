package com.example.risk_engine.model;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class RiskScore {

    private final int totalScore;
    private final Decision decision;
    private final List<RuleResult> ruleResults;
}
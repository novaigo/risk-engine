package com.example.risk_engine.service;

import com.example.risk_engine.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Service responsible for scoring transactions based on a set of rules.
 * Calculates the total risk score, determines the decision (ALLOW / REVIEW / BLOCK),
 * and provides explanations for why certain scores were applied.
 */
@Service
@RequiredArgsConstructor
public class RiskScoringService {

    private final RuleRegistry registry;
    private final DecisionEngine decisionEngine;


    public RiskScore evaluate(Transaction tx) {
        List<RuleResult> results = registry.getRules().stream()
                .map(rule -> rule.evaluate(tx))
                .filter(Objects::nonNull)
                .toList();

        Decision decision = decisionEngine.decide(results);
        int totalScore = results.stream().mapToInt(RuleResult::getEffectiveScore).sum();

        return new RiskScore(totalScore, decision, results);
    }
}

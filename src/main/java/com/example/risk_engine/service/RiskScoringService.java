package com.example.risk_engine.service;

import com.example.risk_engine.model.*;
import com.example.risk_engine.rules.*;
import com.example.risk_engine.rules.config.RuleConfigFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Service responsible for scoring transactions based on a set of rules.
 *
 * Calculates the total risk score, determines the decision (ALLOW / REVIEW / BLOCK),
 * and provides explanations for why certain scores were applied.
 */
@Service
public class RiskScoringService {

    private final RuleRegistry registry;
    private final DecisionEngine decisionEngine;


    public RiskScoringService(RuleRegistry registry, DecisionEngine decisionEngine) {
        this.registry = registry;
        this.decisionEngine = decisionEngine;
    }

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

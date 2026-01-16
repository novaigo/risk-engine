package com.example.risk_engine.model;
import java.util.List;

public class RiskScore {

    private final int totalScore;
    private final Decision decision;
    private final List<RuleResult> ruleResults;

    public RiskScore(int totalScore, Decision decision, List<RuleResult> ruleResults) {
        this.totalScore = totalScore;
        this.decision = decision;
        this.ruleResults = ruleResults;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public Decision getDecision() {
        return decision;
    }

    public List<RuleResult> getRuleResults() {
        return ruleResults;
    }
}
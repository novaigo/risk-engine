package com.example.risk_engine.model;
/**
 * Result of a single rule evaluation.
 * Represents how and why a rule contributed to the final decision.
 */
public class RuleResult {

    private final String ruleName;
    private final boolean triggered;
    private final int score;
    private final String explanation;
    private final Severity severity;
    private final int effectiveScore;

    public RuleResult(String ruleName, boolean triggered, int score, String explanation, Severity severity) {
        this.ruleName = ruleName;
        this.triggered = triggered;
        this.score = score;
        this.explanation = explanation;
        this.severity = severity;
        this.effectiveScore = (int) (score * severity.weight());
    }

    public String getRuleName() {
        return ruleName;
    }

    public boolean isTriggered() {
        return triggered;
    }

    public int getScore() {
        return score;
    }

    public String getExplanation() {
        return explanation;
    }

    public Severity getSeverity() {
        return severity;
    }

    public int getEffectiveScore() {
        return effectiveScore;
    }

    @Override
    public String toString() {
        return "RuleResult{" +
                "ruleName='" + ruleName + '\'' +
                ", triggered=" + triggered +
                ", score=" + score +
                ", explanation='" + explanation + '\'' +
                ", severity=" + severity +
                '}';
    }
}

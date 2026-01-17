package com.example.risk_engine.service;

import com.example.risk_engine.model.Decision;
import com.example.risk_engine.model.RuleResult;
import com.example.risk_engine.model.Severity;
import com.example.risk_engine.rules.config.DecisionProperties;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DecisionEngine {

    private final int reviewThreshold;
    private final int blockThreshold;

    public DecisionEngine(DecisionProperties props) {
        this.reviewThreshold = props.getReviewThreshold();
        this.blockThreshold = props.getBlockThreshold();
    }

    public Decision decide(List<RuleResult> results) {
        int totalScore = results.stream()
                .mapToInt(RuleResult::getEffectiveScore)
                .sum();

        boolean hardBlock = results.stream()
                .anyMatch(r -> r.isTriggered() && r.getSeverity() == Severity.BLOCK);

        if (hardBlock) {
            //separated so we can later decide if the block was hardBlock or based on totalScore
            return Decision.BLOCK;
        }

        if (totalScore >= blockThreshold) {
            return Decision.BLOCK;
        }

        if (totalScore >= reviewThreshold) {
            return Decision.REVIEW;
        }

        return Decision.ALLOW;
    }
}

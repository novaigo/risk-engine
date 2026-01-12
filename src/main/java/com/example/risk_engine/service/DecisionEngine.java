package com.example.risk_engine.service;

import com.example.risk_engine.model.RuleResult;

import java.util.List;

public class DecisionEngine {

    private final int reviewThreshold;
    private final int blockThreshold;

    public DecisionEngine(int reviewThreshold, int blockThreshold) {
        this.reviewThreshold = reviewThreshold;
        this.blockThreshold = blockThreshold;
    }
    public String decide(List<RuleResult> results) {
        int totalScore = results.stream()
                .mapToInt(RuleResult::getScore)
                .sum();

        //todo:
        if(totalScore >=blockThreshold) return "BLOCK";
        if(totalScore >=reviewThreshold) return "REVIEW";
        return "ALLOW";
    }
}

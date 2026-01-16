package com.example.risk_engine.service;

import com.example.risk_engine.model.RuleResult;
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

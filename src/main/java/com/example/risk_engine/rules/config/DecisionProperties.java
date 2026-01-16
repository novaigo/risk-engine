package com.example.risk_engine.rules.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "decision")
public class DecisionProperties {
    private int reviewThreshold;
    private int blockThreshold;

    public int getReviewThreshold() {
        return reviewThreshold;
    }

    public void setReviewThreshold(int reviewThreshold) {
        this.reviewThreshold = reviewThreshold;
    }

    public int getBlockThreshold() {
        return blockThreshold;
    }

    public void setBlockThreshold(int blockThreshold) {
        this.blockThreshold = blockThreshold;
    }
}

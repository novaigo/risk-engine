package com.example.risk_engine.rules.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "decision")
@Data
public class DecisionProperties {
    private int reviewThreshold;
    private int blockThreshold;
}

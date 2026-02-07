package com.example.risk_engine.rules.config;

import com.example.risk_engine.model.Severity;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "risk.rules")
@Data
public class RiskRulesProperties{

    private Amount amount;
    private Velocity velocity;
    private Country country;
    private Hour hour;

    @Data
    public static class Amount {
        private boolean enabled;
        private BigDecimal maxAmount;
        private int score;
        private Severity severity;
    }
    @Data
    public static class Velocity {
        private boolean enabled;
        private int maxTxPerMinute;
        private int score;
        private Severity severity;
    }
    @Data
    public static class Country {
        private boolean enabled;
        private List<String> highRiskCountries;
        private int score;
        private Severity severity;
    }
    @Data
    public static class Hour {
        private boolean enabled;
        private int startHour;
        private int endHour;
        private int score;
        private Severity severity;
    }
}

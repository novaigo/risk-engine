package com.example.risk_engine.rules.config;

import com.example.risk_engine.rules.AmountRule;
import com.example.risk_engine.rules.CountryRule;
import com.example.risk_engine.rules.Rule;
import com.example.risk_engine.rules.VelocityRule;
import org.springframework.stereotype.Component;

@Component
public class RuleConfigFactory {

    private final RiskRulesProperties properties;

    public RuleConfigFactory(RiskRulesProperties properties) {
        this.properties = properties;
    }

    public Rule amountRule() {
        var cfg = properties.getAmount();

        if (!cfg.isEnabled()) {
            return null;
        }

        return new AmountRule(
                new AmountRuleConfig(cfg.getMaxAmount(), cfg.getScore(), cfg.getSeverity())
        );
    }
    public Rule velocityRule() {
        var cfg = properties.getVelocity();

        if (!cfg.isEnabled()) {
            return null;
        }

        return new VelocityRule(
                new VelocityRuleConfig(cfg.getMaxTxPerMinute(), cfg.getScore(), cfg.getSeverity())
        );
    }
    public Rule countryRule() {
        var cfg = properties.getCountry();

        if (!cfg.isEnabled()) {
            return null;
        }

        return new CountryRule(
                new CountryRuleConfig(cfg.getHighRiskCountries(), cfg.getScore(), cfg.getSeverity())
        );
    }
}

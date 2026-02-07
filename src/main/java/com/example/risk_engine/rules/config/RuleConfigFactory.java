package com.example.risk_engine.rules.config;

import com.example.risk_engine.rules.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class RuleConfigFactory {

    private final RiskRulesProperties properties;

    //C config type ex.AmountRuleConfig, R rule type ex AmountRule
    private <C, R extends Rule> R createRule(C cfg, boolean enabled, Function<C, R> constructor) {
        return enabled ? constructor.apply(cfg) : null;
    }

    public Rule amountRule() {
        var cfg = properties.getAmount();
        return createRule(
                cfg,
                cfg.isEnabled(),
                c -> new AmountRule(
                        new AmountRuleConfig(c.getMaxAmount(), c.getScore(), c.getSeverity())));
    }

    public Rule velocityRule() {
        var cfg = properties.getVelocity();
        return createRule(
                cfg,
                cfg.isEnabled(),
                c -> new VelocityRule(
                        new VelocityRuleConfig(c.getMaxTxPerMinute(), c.getScore(), c.getSeverity())));
    }

    public Rule countryRule() {
        var cfg = properties.getCountry();

        return createRule(
                cfg,
                cfg.isEnabled(),
                c -> new CountryRule(
                        new CountryRuleConfig(c.getHighRiskCountries(), c.getScore(), c.getSeverity())));
    }

    public Rule hourRule() {
        var cfg = properties.getHour();

        return createRule(
                cfg,
                cfg.isEnabled(),
                c -> new HourRule(
                        new HourRuleConfig(c.getStartHour(), c.getEndHour(), c.getScore(), c.getSeverity())));

    }
}

package com.example.risk_engine.service;

import com.example.risk_engine.rules.config.RuleConfigFactory;
import org.springframework.stereotype.Component;

@Component
public class RulesInitializer {
    public RulesInitializer(RuleRegistry registry, RuleConfigFactory factory) {
        registry.clear();
        registry.register(factory.amountRule());
        registry.register(factory.velocityRule());
        registry.register(factory.hourRule());
        registry.register(factory.countryRule());
    }
}

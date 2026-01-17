package com.example.risk_engine.service;

import com.example.risk_engine.rules.Rule;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Registry holding all active rules for the risk engine.
 * Supports dynamic enable/disable and centralized management.
 */
@Component
public class RuleRegistry {
    private final List<Rule> rules;

    public RuleRegistry(List<Rule> rules) {
        this.rules = new ArrayList<>(rules);
    }

    public void register(Rule rule) {
        if (rule != null) {
            rules.add(rule);
        }
    }
    public List<Rule> getRules() {
        return List.copyOf(rules);
    }
    public void clear() {
        rules.clear();
    }
}

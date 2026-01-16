package com.example.risk_engine.rules;

import com.example.risk_engine.model.RuleResult;
import com.example.risk_engine.model.Severity;
import com.example.risk_engine.model.Transaction;
import com.example.risk_engine.rules.config.HourRuleConfig;

public class HourRule implements Rule {

    private final HourRuleConfig config;

    public HourRule(HourRuleConfig config) {
        this.config = config;
    }

    @Override
    public RuleResult evaluate(Transaction tx) {
        int txTime = tx.getHour();
        boolean triggered = txTime >= config.startHour() && txTime <= config.endHour();
        int score = triggered ? config.score() : 0;
        Severity severity = triggered ? config.severity() : Severity.INFO;

        return new RuleResult(
                "HourRule",
                triggered,
                score,
                triggered ? "Transaction executed at risky hour: " + txTime : "Hour normal",
                severity
        );
    }
}
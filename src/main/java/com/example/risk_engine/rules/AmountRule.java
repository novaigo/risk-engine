package com.example.risk_engine.rules;

import com.example.risk_engine.model.RuleResult;
import com.example.risk_engine.model.Severity;
import com.example.risk_engine.model.Transaction;
import com.example.risk_engine.rules.config.AmountRuleConfig;

/**
 * Rule evaluating transaction amount risk.
 */
public class AmountRule implements Rule{

    private final AmountRuleConfig config;

    public AmountRule(AmountRuleConfig config) {
        this.config = config;
    }

    @Override
    public RuleResult evaluate(Transaction tx){
        boolean triggered = tx.getAmount() > config.maxAmount();
        int score = triggered ? config.score() : 0;
        Severity severity = triggered ? config.severity() : Severity.INFO;
            return new RuleResult(
                    "AmountRule",
                    triggered,
                    score,
                    triggered ? "Transaction amount exceeds " + config.maxAmount() : "Transaction amount normal",
                    severity
            );
    }
}

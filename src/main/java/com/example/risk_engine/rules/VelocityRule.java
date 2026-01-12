package com.example.risk_engine.rules;

import com.example.risk_engine.model.RuleResult;
import com.example.risk_engine.model.Severity;
import com.example.risk_engine.model.Transaction;
import com.example.risk_engine.rules.config.VelocityRuleConfig;

import java.util.List;

/**
 * Rule evaluating transaction velocity risk.
 */
public class VelocityRule implements Rule{

    private final VelocityRuleConfig config;

    public VelocityRule(VelocityRuleConfig config){
        this.config = config;
    }

    @Override
    public RuleResult evaluate(Transaction tx) {
        //todo:
        int txCount = tx.getTransactionsInLastMinute();

        boolean triggered = txCount > config.getMaxTxPerMinute();
        int score = triggered ? config.getScore() : 0;
        Severity severity = triggered ? config.getSeverity(): Severity.INFO;

        return new RuleResult(
                "VelocityRule",
                triggered,
                score,
                triggered ? "Too many transactions in short period: " + txCount : "Transaction velocity normal",
                severity
        );
    }
}

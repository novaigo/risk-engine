package com.example.risk_engine.rules;

import com.example.risk_engine.model.RuleResult;
import com.example.risk_engine.model.Transaction;

import java.util.List;


/**
 * Interface for a risk evaluation rule.
 */
public interface Rule {
    RuleResult evaluate(Transaction tx);
}

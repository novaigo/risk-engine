package com.example.risk_engine.rules;

import com.example.risk_engine.model.Transaction;

import java.util.List;

public class HourRule implements Rule{

    @Override
    public int apply(Transaction tx, List<String> explanations) {
        if (tx.getHour() < 6 || tx.getHour() > 22) {
            explanations.add("Transaction performed during unusual hours: " + tx.getHour());
            return 10;
        }
        return 0;
    }
}

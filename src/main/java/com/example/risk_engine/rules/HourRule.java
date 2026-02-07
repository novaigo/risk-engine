package com.example.risk_engine.rules;

import com.example.risk_engine.model.RuleResult;
import com.example.risk_engine.model.Severity;
import com.example.risk_engine.model.Transaction;
import com.example.risk_engine.rules.config.HourRuleConfig;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequiredArgsConstructor
public class HourRule implements Rule {

    private final HourRuleConfig config;

    @Override
    public RuleResult evaluate(Transaction tx) {

        ZoneId zone = ZoneId.of(tx.getTimezone() != null ? tx.getTimezone() : "UTC");
        ZonedDateTime txTime = tx.getTimestamp().atZone(zone);
        int txHour = txTime.getHour();

        boolean triggered = txHour >= config.startHour() && txHour < config.endHour();
        int score = triggered ? config.score() : 0;
        Severity severity = triggered ? config.severity() : Severity.INFO;

        return new RuleResult(
                "HourRule",
                triggered,
                score,
                triggered ? "Transaction executed at risky hour: " + txHour : "Hour normal",
                severity
        );
    }
}
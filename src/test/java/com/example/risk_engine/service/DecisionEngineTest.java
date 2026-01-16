package com.example.risk_engine.service;

import com.example.risk_engine.model.Decision;
import com.example.risk_engine.model.RuleResult;
import com.example.risk_engine.model.Severity;
import com.example.risk_engine.rules.config.DecisionProperties;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DecisionEngineTest {

    private DecisionEngine engine() {
        DecisionProperties props = new DecisionProperties();
        props.setReviewThreshold(50);
        props.setBlockThreshold(100);
        return new DecisionEngine(props);
    }

    @Test
    void blockSeverity() {
        DecisionEngine engine = engine();

        List<RuleResult> results = List.of(
                new RuleResult("A", true, 10, "ok", Severity.INFO),
                new RuleResult("B", true, 0, "bad", Severity.BLOCK)
        );

        Decision decision = engine.decide(results);

        assertThat(decision).isEqualTo(Decision.BLOCK);
    }

    @Test
    void reviewThreshold() {
        DecisionEngine engine = engine();

        List<RuleResult> results = List.of(
                new RuleResult("A", true, 30, "ok", Severity.INFO),
                new RuleResult("B", true, 25, "ok", Severity.INFO)
        );

        Decision decision = engine.decide(results);

        assertThat(decision).isEqualTo(Decision.REVIEW);
    }
    //score above threshold
    @Test
    void blockThreshold() {
        DecisionEngine engine = engine();

        List<RuleResult> results = List.of(
                new RuleResult("A", true, 60, "ok", Severity.INFO),
                new RuleResult("B", true, 50, "ok", Severity.INFO)
        );

        Decision decision = engine.decide(results);

        assertThat(decision).isEqualTo(Decision.BLOCK);
    }

    @Test
    void allow() {
        DecisionEngine engine = engine();

        List<RuleResult> results = List.of(
                new RuleResult("A", false, 0, "ok", Severity.INFO),
                new RuleResult("B", false, 0, "ok", Severity.INFO)
        );

        Decision decision = engine.decide(results);

        assertThat(decision).isEqualTo(Decision.ALLOW);
    }
}

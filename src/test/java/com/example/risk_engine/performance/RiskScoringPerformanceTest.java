package com.example.risk_engine.performance;

import com.example.risk_engine.model.Decision;
import com.example.risk_engine.model.RiskScore;
import com.example.risk_engine.model.Severity;
import com.example.risk_engine.model.Transaction;
import com.example.risk_engine.rules.config.DecisionProperties;
import com.example.risk_engine.rules.config.RiskRulesProperties;
import com.example.risk_engine.rules.config.RuleConfigFactory;
import com.example.risk_engine.service.DecisionEngine;
import com.example.risk_engine.service.RiskScoringService;
import com.example.risk_engine.service.RuleRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTimeout;

public class RiskScoringPerformanceTest {

    private RiskScoringService scoringService;

    @BeforeEach
    void setup() {
        //config engine
        DecisionProperties decProps = new DecisionProperties();
        decProps.setReviewThreshold(50);
        decProps.setBlockThreshold(80);
        DecisionEngine decisionEngine = new DecisionEngine(decProps);

        RiskRulesProperties rrProps = new RiskRulesProperties();

        RiskRulesProperties.Amount amount = new RiskRulesProperties.Amount();
        amount.setEnabled(true);
        amount.setScore(40);
        amount.setMaxAmount(BigDecimal.valueOf(120));
        amount.setSeverity(Severity.BLOCK);
        rrProps.setAmount(amount);

        RiskRulesProperties.Velocity velocity = new RiskRulesProperties.Velocity();
        velocity.setEnabled(true);
        velocity.setScore(100);
        velocity.setScore(3);
        velocity.setSeverity(Severity.REVIEW);
        rrProps.setVelocity(velocity);

        RiskRulesProperties.Hour hour = new RiskRulesProperties.Hour();
        hour.setEnabled(true);
        hour.setStartHour(0);
        hour.setEndHour(5);
        hour.setScore(20);
        hour.setSeverity(Severity.REVIEW);
        rrProps.setHour(hour);

        RiskRulesProperties.Country country = new RiskRulesProperties.Country();
        country.setEnabled(true);
        country.setHighRiskCountries(List.of("KP"));
        country.setSeverity(Severity.BLOCK);
        country.setScore(30);
        rrProps.setCountry(country);

        RuleConfigFactory factory = new RuleConfigFactory(rrProps);
        //if some rule is disabled needs to be filtered
        RuleRegistry registry = new RuleRegistry(Stream.of(
                factory.amountRule(),
                factory.countryRule(),
                factory.hourRule(),
                factory.velocityRule()
                //if some rule is disabled needs to be filtered
        ).filter(Objects::nonNull).toList()
        );

        scoringService = new RiskScoringService(registry, decisionEngine);
    }

    @Test
    void highThroughputTest() {
        Random random = new Random();
        List<Transaction> txs = IntStream.range(0, 100_000)
                .mapToObj(i -> {
                    Transaction tx = new Transaction();
                    //30-50
                    tx.setAmount(BigDecimal.valueOf(30 + random.nextInt(21)));
                    tx.setCountry("AT");
                    tx.setTimestamp(LocalDateTime.now());
                    tx.setVelocity(1 % 10);
                    return tx;
                })
                .collect(Collectors.toList());

        Transaction highRiskCountryTx = new Transaction();
        highRiskCountryTx.setAmount(BigDecimal.valueOf((50)));
        highRiskCountryTx.setCountry("KP");
        highRiskCountryTx.setTimestamp(LocalDateTime.now());
        highRiskCountryTx.setVelocity(1);

        Transaction amountTx = new Transaction();
        amountTx.setAmount(BigDecimal.valueOf(2000));
        amountTx.setCountry("RU");
        amountTx.setTimestamp(LocalDateTime.now());
        amountTx.setVelocity(5);

        txs.add(0, highRiskCountryTx);
        txs.add(1000, amountTx);

        long start = System.currentTimeMillis();
        List<RiskScore> scores = txs.parallelStream()
                .map(tx -> scoringService.evaluate(tx))
                .toList();
        long end = System.currentTimeMillis();
        System.out.println("Processed 100_000 tx in " + (end - start) + "ms");


        assertThat(scores.get(0).getDecision()).isEqualTo(Decision.BLOCK);
        assertThat(scores.get(1000).getDecision()).isEqualTo(Decision.BLOCK);
        assertTimeout(Duration.ofMinutes(1), () -> {});
    }
}

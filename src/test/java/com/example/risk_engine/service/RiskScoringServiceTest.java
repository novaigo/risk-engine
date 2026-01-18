package com.example.risk_engine.service;

import com.example.risk_engine.model.Decision;
import com.example.risk_engine.model.RiskScore;
import com.example.risk_engine.model.Severity;
import com.example.risk_engine.model.Transaction;
import com.example.risk_engine.rules.config.DecisionProperties;
import com.example.risk_engine.rules.config.RiskRulesProperties;
import com.example.risk_engine.rules.config.RuleConfigFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

class RiskScoringServiceTest {

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
    //should be blocked because of the country
    void highRiskCountry(){
        Transaction tx = new Transaction();
        LocalDateTime timestamp = LocalDateTime.of(2026,1, 16,20, 0 ); //16.1.2026 -- 20:00
        tx.setTimestamp(timestamp);
        tx.setCountry("KP");
        tx.setAmount(BigDecimal.valueOf(10));
        tx.setVelocity(1);

        RiskScore score = scoringService.evaluate(tx);
        assertThat(score.getDecision()).isEqualTo(Decision.BLOCK);
    }
    @Test
    //normal transaction should be allowed
    void shouldPass(){
        Transaction tx = new Transaction();
        LocalDateTime timestamp = LocalDateTime.of(2026,1, 16,20, 0 ); //16.1.2026 -- 20:00
        tx.setTimestamp(timestamp);
        tx.setCountry("AT");
        tx.setAmount(BigDecimal.valueOf(100));
        tx.setVelocity(2);

        RiskScore score = scoringService.evaluate(tx);
        assertThat(score.getRuleResults()).hasSize(4);
        assertThat(score.getDecision()).isEqualTo(Decision.ALLOW);
    }
}

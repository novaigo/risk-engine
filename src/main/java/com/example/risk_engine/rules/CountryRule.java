package com.example.risk_engine.rules;

import com.example.risk_engine.model.RuleResult;
import com.example.risk_engine.model.Severity;
import com.example.risk_engine.model.Transaction;
import com.example.risk_engine.rules.config.CountryRuleConfig;

import java.util.Set;

public class CountryRule implements Rule {

    private final CountryRuleConfig config;
    private static final Set<String> DEFAULT_RISKY_COUNTRIES = Set.of(
            "AF", // Afghanistan
            "DZ", // Algeria
            "AO", // Angola
            "BO", // Bolivia
            "VG", // British Virgin Islands
            "CM", // Cameroon
            "CI", // Côte d’Ivoire
            "CD", // Democratic Republic of the Congo
            "HT", // Haiti
            "IR", // Iran
            "KE", // Kenya
            "LA", // Laos
            "LB", // Lebanon
            "ML", // Mali
            "MC", // Monaco (generally not high-risk)
            "MM", // Myanmar
            "NA", // Namibia
            "NP", // Nepal
            "KP", // North Korea
            "RU", // Russia
            "SS", // South Sudan
            "SY", // Syria
            "TT", // Trinidad and Tobago
            "VU", // Vanuatu
            "VE", // Venezuela
            "VN", // Vietnam
            "YE"  // Yemen
    );

    public CountryRule(CountryRuleConfig config) {
        this.config = config;
    }

    @Override
    public RuleResult evaluate(Transaction tx) {
        String country = tx.getCountry();

        Set<String> highRisk = config.getHighRiskCountries() != null && !config.getHighRiskCountries().isEmpty()
                ? Set.copyOf(config.getHighRiskCountries())
                : DEFAULT_RISKY_COUNTRIES;

        boolean triggered = highRisk.contains(country);
        int score = triggered ? config.getScore() : 0;
        Severity severity = triggered ? config.getSeverity() : Severity.INFO;

        return new RuleResult(
                "CountryRule",
                triggered,
                score,
                triggered ? "Transaction from risky country: " + country : "Country normal",
                severity
        );

    }
}
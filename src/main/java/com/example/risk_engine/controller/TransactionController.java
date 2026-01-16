package com.example.risk_engine.controller;

import com.example.risk_engine.model.RiskScore;
import com.example.risk_engine.model.Transaction;
import com.example.risk_engine.service.RiskScoringService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final RiskScoringService riskScoringService;

    public TransactionController(RiskScoringService riskScoringService) {
        this.riskScoringService = riskScoringService;
    }

    @PostMapping("/score")
    public RiskScore score(@RequestBody Transaction transaction) {
        return riskScoringService.evaluate(transaction);
    }
}

package com.spring.ai.chatclient.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.spring.ai.chatclient.entity.TransactionEntity;
import com.spring.ai.chatclient.model.RiskAssessment;

@Service
public class RiskAssessmentService {
	
	public RiskAssessment assessTransaction(TransactionEntity transaction) {

        List<String> factors = new ArrayList<>();
        int score = 0;
        
        // Demonstration rule, not a production fraud model
        if (transaction.getAmount().compareTo(new BigDecimal("50000")) > 0) {
            score += 50;
            factors.add("Amount exceeds the configured threshold");
        }

        if ("ONLINE".equalsIgnoreCase(transaction.getTransactionType())) {
            score += 10;
            factors.add("Online transaction");
        }

        RiskAssessment result = new RiskAssessment();
        result.setRiskScore(score);
        result.setRiskLevel(score >= 50 ? "HIGH" : score >= 25 ? "MEDIUM" : "LOW");
        result.setRiskFactors(factors);
        return result;
    }
}

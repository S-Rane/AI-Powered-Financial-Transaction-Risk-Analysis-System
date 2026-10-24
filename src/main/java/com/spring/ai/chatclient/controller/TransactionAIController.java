package com.spring.ai.chatclient.controller;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.spring.ai.chatclient.repository.TransactionAIAnalysisRepository;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionAIController {
	
	private final TransactionAIAnalysisRepository aiAnalysisRepository;
	
	public TransactionAIController(TransactionAIAnalysisRepository aiAnalysisRepository) {
		this.aiAnalysisRepository = aiAnalysisRepository;
	}

	@GetMapping("/{transactionId}/ai-analysis")
    public ResponseEntity<?> analyzeTransaction(@PathVariable String transactionId) {

		return aiAnalysisRepository.findByTransactionId(transactionId)
		        .filter(a -> "COMPLETED".equals(a.getStatus()))
		        .<ResponseEntity<?>>map(ResponseEntity::ok)
		        .orElseGet(() -> ResponseEntity.status(HttpStatus.ACCEPTED)
		        .body(Map.of(
		                "transactionId", transactionId,
		                "status", "PENDING",
		                "message", "AI analysis is not yet available for transactionId: "+transactionId)));
    }

}

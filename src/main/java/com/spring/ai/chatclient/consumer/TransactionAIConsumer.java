package com.spring.ai.chatclient.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import com.spring.ai.chatclient.events.TransactionEvent;
import com.spring.ai.chatclient.service.TransactionAIService;

@Component
public class TransactionAIConsumer {
	
     private static final Logger logger = LoggerFactory.getLogger(TransactionAIConsumer.class);
	
	 private final TransactionAIService transactionAIService;

	 public TransactionAIConsumer(TransactionAIService transactionAIService) {
	        this.transactionAIService = transactionAIService;
	  	}

	 @KafkaListener(topics = "transaction-created", groupId = "transaction-ai-group")
	 public void consume(TransactionEvent event) {
	     transactionAIService.analyzeTransaction(event.getTransactionId());
	     logger.info("Transaction-event received successfully{}",event.toString());
	}
}

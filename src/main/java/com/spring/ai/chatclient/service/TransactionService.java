package com.spring.ai.chatclient.service;

import java.util.UUID;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.spring.ai.chatclient.dto.TransactionReqDTO;
import com.spring.ai.chatclient.entity.TransactionEntity;
import com.spring.ai.chatclient.events.TransactionEvent;
import com.spring.ai.chatclient.repository.TransactionRepository;

@Service
public class TransactionService {
	
	private final KafkaTemplate<String, TransactionEvent> kafkaTemplate;
	private final TransactionRepository transactionRepository;

    public TransactionService(KafkaTemplate<String, TransactionEvent> kafkaTemplate, TransactionRepository transactionRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public TransactionEntity createTransaction(TransactionReqDTO reqDTO) {

    	// 1. Validate transaction
        // 2. Save transaction
        // 3. Publish transaction event
    	
    TransactionEntity transactionEntity = new TransactionEntity();
    
    String transactionId = "TXN-" + UUID.randomUUID();
    transactionEntity.setTransactionId(transactionId);
    
    /*Setting initial status to PROCESSING*/
    transactionEntity.setStatus("PROCESSING");
    
    /*Mapping reqDTO to TransactionEntity*/
    transactionEntity.setCustomerId(reqDTO.getCustomerId());
    transactionEntity.setAmount(reqDTO.getAmount());
    transactionEntity.setCurrency(reqDTO.getCurrency());
    transactionEntity.setLocation(reqDTO.getLocation());
    transactionEntity.setMerchantId(reqDTO.getMerchantId());
    transactionEntity.setTransactionType(reqDTO.getTransactionType());
    
    /*Inserting in DB*/
    TransactionEntity savedTransaction = transactionRepository.save(transactionEntity);

    TransactionEvent event = new TransactionEvent(transactionId,  savedTransaction.getCustomerId(), savedTransaction.getAmount(),
    		savedTransaction.getCurrency(), savedTransaction.getMerchantId(), savedTransaction.getTransactionType(), savedTransaction.getLocation());
	               
    kafkaTemplate.send("transaction-created",transactionId,event);
    return savedTransaction;
    }
}

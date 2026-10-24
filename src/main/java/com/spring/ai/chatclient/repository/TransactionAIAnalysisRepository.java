package com.spring.ai.chatclient.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.ai.chatclient.entity.TransactionAIEntity;

public interface TransactionAIAnalysisRepository extends JpaRepository<TransactionAIEntity, Long> {
	
	Optional<TransactionAIEntity> findByTransactionId(String transactionId);

}

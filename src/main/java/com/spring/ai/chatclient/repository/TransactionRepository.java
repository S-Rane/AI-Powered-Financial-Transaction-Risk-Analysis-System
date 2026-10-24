package com.spring.ai.chatclient.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.ai.chatclient.entity.TransactionEntity;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
	
	 Optional<TransactionEntity> findByTransactionId(String transactionId);

}

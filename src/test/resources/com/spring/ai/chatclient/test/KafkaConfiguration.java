package com.spring.ai.chatclient.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(
        partitions = 1,
        topics = {"transaction-created"},
        controlledShutdown = true,
        bootstrapServersProperty = "spring.kafka.bootstrap-servers")
public class KafkaConfiguration {
	
	@Test
	void testKafkaSetup() {
		System.out.println("Embedded Kafka is running!");
	}
}

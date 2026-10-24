
package com.spring.ai.chatclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class ChatclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatclientApplication.class, args);
	}

}

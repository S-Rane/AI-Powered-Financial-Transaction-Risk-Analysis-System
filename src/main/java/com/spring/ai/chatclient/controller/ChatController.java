package com.spring.ai.chatclient.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The ChatClient is created using a ChatClient.Builder object
 * The call() method sends a request to the AI model, and the content() 
 * method returns the AI model’s response as a String.
 */
@RestController
@RequestMapping("/ai")
public class ChatController {
	
	private final ChatClient chatClient;
	
    public ChatController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }
    
    @GetMapping(value = "/chat")
    public String generation(@RequestParam(defaultValue = "Tell me a joke") String message) {
    		     
    	return this.chatClient.prompt()
		.user(message)
	    .call()
	    .content();
	     
    }
}

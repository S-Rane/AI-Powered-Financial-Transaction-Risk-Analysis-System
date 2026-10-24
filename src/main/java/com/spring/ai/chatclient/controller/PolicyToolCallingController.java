package com.spring.ai.chatclient.controller;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.spring.ai.chatclient.service.PolicyToolCallingService;

@RestController
@RequestMapping("/api/ai/tools")
public class PolicyToolCallingController {
	
	private static final Logger logger = LoggerFactory.getLogger(PolicyToolCallingController.class);
	
	private final PolicyToolCallingService callingService;

	public PolicyToolCallingController(PolicyToolCallingService callingService) {
		this.callingService = callingService;
	}
	
	@GetMapping("/policy-search")
    public Map<String, String> searchPolicies(@RequestParam String question) {
		
		logger.info("User Prompt{}",question);
        String answer = callingService.askAboutRiskPolicies(question);
        logger.info("LLM generated ans{}",answer);
        return Map.of("answer", answer);
    }
	
	

}

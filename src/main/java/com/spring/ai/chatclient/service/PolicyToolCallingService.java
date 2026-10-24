package com.spring.ai.chatclient.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import com.spring.ai.chatclient.tool.TransactionRiskPolicyTool;

@Service
public class PolicyToolCallingService {
	
	private static final Logger logger = LoggerFactory.getLogger(PolicyToolCallingService.class);
	
	private final ChatClient chatClient;
	private final TransactionRiskPolicyTool policyTool;
	
	public PolicyToolCallingService(ChatClient chatClient, TransactionRiskPolicyTool policyTool) {
		this.chatClient = chatClient;
		this.policyTool = policyTool;
	}
	
	public String askAboutRiskPolicies(String userPrompt) {
		
		logger.info("User Prompt{}",userPrompt);
		return chatClient.prompt().system("""
                    You are a financial transaction risk assistant.
                    When answering a question about transaction risk
                    policies, use the searchTransactionRiskPolicies
                    tool to retrieve relevant policy evidence first.
                    Base your answer on the retrieved policies.
                    If no relevant policy is found, clearly say so.
                    Do not invent policy rules or transaction facts.
                    """)
            .user(userPrompt)
            .tools(policyTool)
            .call()
            .content();
		
		/*.tools(policyTool). It makes your Java tool available for this particular LLM request. 
		 * Spring AI manages the tool-call round trip*/
	}
}

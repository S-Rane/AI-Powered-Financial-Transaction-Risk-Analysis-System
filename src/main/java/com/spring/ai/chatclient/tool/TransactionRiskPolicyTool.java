package com.spring.ai.chatclient.tool;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;
import com.spring.ai.chatclient.service.PolicyRetrievalService;

/**
 * @Component lets Spring manage the tool as a bean.
   @Tool exposes the method's name and description to the model.
   @ToolParam describes the input the model should provide. 
 */
@Component
public class TransactionRiskPolicyTool {
	
	private static final Logger logger = LoggerFactory.getLogger(TransactionRiskPolicyTool.class);
	
	private final PolicyRetrievalService policyRetrievalService;
	
	public TransactionRiskPolicyTool(PolicyRetrievalService policyRetrievalService) {
		this.policyRetrievalService = policyRetrievalService;
	}
	@Tool(name = "searchTransactionRiskPolicies",
		  description = """
            Search the transaction risk policy documents for rules
            relevant to a transaction risk question. Use this tool
            when you need policy evidence about transaction amount,
            location, online purchases, or risk review requirements.
            The query should describe the specific risk question.
            """)
	public String searchTransactionRiskPolicies(@ToolParam(description = "The transaction risk question to search policies for")
	String query) {
		
		logger.info("LLM generated query{}",query);
		List<Document> documents = policyRetrievalService.retrievePolicies(query);
		logger.info("List of policies retrived from RAG{}",documents.toString());

	    if (documents == null || documents.isEmpty()) {
	         return "No relevant transaction risk policies were found.";
	    }
	    
	    return documents.stream().map(Document::getText).collect(Collectors.joining("\n\n---\n\n"));
	}
}

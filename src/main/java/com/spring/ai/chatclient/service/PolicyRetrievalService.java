package com.spring.ai.chatclient.service;

import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

@Service
public class PolicyRetrievalService {
	
	private final VectorStore vectorStore;

	public PolicyRetrievalService(VectorStore vectorStore) {
		this.vectorStore = vectorStore;
	}
	
	public List<Document> retrievePolicies(String query) {
        return vectorStore.similaritySearch(
            SearchRequest.builder()
                .query(query)
                .topK(3)
                .build());
    }
}

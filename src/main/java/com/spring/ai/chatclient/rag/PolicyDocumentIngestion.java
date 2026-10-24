package com.spring.ai.chatclient.rag;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class PolicyDocumentIngestion implements CommandLineRunner {
	
	private static final Logger log = LoggerFactory.getLogger(PolicyDocumentIngestion.class);
    private final Resource policyResource;
    private final VectorStore vectorStore;

    public PolicyDocumentIngestion(@Value("classpath:/rag/policies/transaction-risk-policy.txt")Resource policyResource,
    		VectorStore vectorStore) {
        this.policyResource = policyResource;
        this.vectorStore = vectorStore;
    }

    @Override
    public void run(String... args) {
        // 1. Read the text file as a Spring AI Document
    	/*TextReader, which loads the policy file into a Document*/
        TextReader reader = new TextReader(policyResource);
        reader.getCustomMetadata().put("documentType", "transaction-risk-policy");

        List<Document> documents = reader.read();
        log.info("Source documents loaded: {}", documents.size());

        // 2. Split documents into smaller chunks
        /*TokenTextSplitter, which divides long documents into smaller chunks*/
        TokenTextSplitter splitter = TokenTextSplitter.builder()
                .withChunkSize(100)
                .withMinChunkSizeChars(40)
                .withMinChunkLengthToEmbed(10)
                .build();

        List<Document> chunks = splitter.apply(documents);
        log.info("Policy chunks created: {}", chunks.size());
        
        /*Adding chunks to vector store*/
        /*vectorStore.add(chunks) executes, Spring AI will generate embeddings for the chunks and store them*/
        vectorStore.add(chunks);
        log.info("Successfully stored policy chunks in the vector store {}",chunks.size());
        
        /*Displaying vector store based on user query for testing RAG - Retrieval*/
		/*
		 * List<Document> results = vectorStore.similaritySearch(
		 * SearchRequest.builder()
		 * .query("What should happen when a transaction exceeds INR 100000?") .topK(10)
		 * .build()); log.info("Retrieved policy chunks{} ",results.size());
		 * 
		 * for (Document document : results) { log.info("ID{}",document.getId());
		 * log.info("Metadata{}",document.getMetadata());
		 * log.info("Content{}",document.getText());
		 * log.info("--------------------------------"); }
		 */
        
        /*Displaying chunk metadata*/
        for (int i = 0; i < chunks.size(); i++) {
            Document chunk = chunks.get(i);
            log.info("Chunk {} metadata: {}", i + 1,chunk.getMetadata());
            log.info("Chunk {} content:\n{}", i + 1,chunk.getText());
        }
    }
}

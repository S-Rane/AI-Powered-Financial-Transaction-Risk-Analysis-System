package com.spring.ai.chatclient.model;

import java.util.List;

public class RiskAssessment {
	
	private String transactionId;
    private int riskScore;
    private String riskLevel;
    private List<String> riskFactors;
	
    public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public int getRiskScore() {
		return riskScore;
	}
	public void setRiskScore(int riskScore) {
		this.riskScore = riskScore;
	}
	public String getRiskLevel() {
		return riskLevel;
	}
	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}
	public List<String> getRiskFactors() {
		return riskFactors;
	}
	public void setRiskFactors(List<String> riskFactors) {
		this.riskFactors = riskFactors;
	}
}

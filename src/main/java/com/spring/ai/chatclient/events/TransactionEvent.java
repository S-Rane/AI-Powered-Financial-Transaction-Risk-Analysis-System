package com.spring.ai.chatclient.events;

import java.math.BigDecimal;

public class TransactionEvent {
	
	private String transactionId;
	private String customerId;
	private BigDecimal amount;
	private String currency;
    private String merchantId;
    private String transactionType;
    private String location;
	
    public TransactionEvent(String transactionId, String customerId, BigDecimal amount, String currency,
			String merchantId, String transactionType, String location) {
		super();
		this.transactionId = transactionId;
		this.customerId = customerId;
		this.amount = amount;
		this.currency = currency;
		this.merchantId = merchantId;
		this.transactionType = transactionType;
		this.location = location;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}

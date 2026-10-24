package com.spring.ai.chatclient.dto;

public class TransactionResDTO {
	
	private String transactionId;
    private String status;
    private String message;

    public TransactionResDTO(String transactionId, String status, String message) {
        this.transactionId = transactionId;
        this.status = status;
        this.message = message;
	  }

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "TransactionResDTO [transactionId=" + transactionId + ", status=" + status + ", message=" + message
				+ "]";
	}
}

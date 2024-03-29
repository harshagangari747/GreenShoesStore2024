package com.store.greenShoes.model;

public class PaymentResponse {
	
	    private boolean success;
	    private String message;

	    // Constructors, getters, and setters
	    public PaymentResponse() {
	    }

	    public PaymentResponse(boolean success, String message) {
	        this.success = success;
	        this.message = message;
	    }

	    // Standard getters and setters
	    public boolean isSuccess() {
	        return success;
	    }

	    public void setSuccess(boolean success) {
	        this.success = success;
	    }

	    public String getMessage() {
	        return message;
	    }

	    public void setMessage(String message) {
	        this.message = message;
	    }
	}



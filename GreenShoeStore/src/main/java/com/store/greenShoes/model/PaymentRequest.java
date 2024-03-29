package com.store.greenShoes.model;

public class PaymentRequest {
	

	
	    private float amount;
	    private String method; // E.g., "credit_card", "paypal"

	    // Constructors, getters, and setters
	    public PaymentRequest() {
	    }

	    public PaymentRequest(float amount, String method) {
	        this.amount = amount;
	       
	        this.method = method;
	    }

	    // Standard getters and setters
	    

	    

	    public float getAmount() {
			return amount;
		}

		public void setAmount(float amount) {
			this.amount = amount;
		}

		

	    public String getMethod() {
	        return method;
	    }

	    public void setMethod(String method) {
	        this.method = method;
	    }
	}



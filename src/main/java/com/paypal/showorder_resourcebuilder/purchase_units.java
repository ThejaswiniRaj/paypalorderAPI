package com.paypal.showorder_resourcebuilder;

public class purchase_units {
	
	private String reference_id;
	private amount amount;
	private payee payee;
	public String getReference_id() {
		return reference_id;
	}
	public void setReference_id(String reference_id) {
		this.reference_id = reference_id;
	}
	public amount getAmount() {
		return amount;
	}
	public void setAmount(amount amount) {
		this.amount = amount;
	}
	public payee getPayee() {
		return payee;
	}
	public void setPayee(payee payee) {
		this.payee = payee;
	}
	
	

}

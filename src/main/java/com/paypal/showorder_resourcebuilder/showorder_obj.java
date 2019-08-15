package com.paypal.showorder_resourcebuilder;

import java.util.List;

public class showorder_obj {
	private String id;
	private String intent;
	private String create_time;
	private String status;
	private List<purchase_units>purchase_units;
	private List<links> links;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIntent() {
		return intent;
	}
	public void setIntent(String intent) {
		this.intent = intent;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<purchase_units> getPurchase_units() {
		return purchase_units;
	}
	public void setPurchase_units(List<purchase_units> purchase_units) {
		this.purchase_units = purchase_units;
	}
	public List<links> getLinks() {
		return links;
	}
	public void setLinks(List<links> links) {
		this.links = links;
	}
	
	

}

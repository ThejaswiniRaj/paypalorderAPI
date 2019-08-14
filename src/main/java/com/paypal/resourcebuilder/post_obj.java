package com.paypal.resourcebuilder;

import java.util.List;

public class post_obj {
private String intent;
private List<Purchase_units>purchase_units;
public String getIntent() {
	return intent;
}
public void setIntent(String intent) {
	this.intent = intent;
}
public List<Purchase_units> getPurchase_units() {
	return purchase_units;
}
public void setPurchase_units(List<Purchase_units> purchase_units) {
	this.purchase_units = purchase_units;
}
}

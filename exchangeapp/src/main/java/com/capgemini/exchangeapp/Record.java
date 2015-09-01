package com.capgemini.exchangeapp;

public class Record{
private String companyName;
private float price;



public Record(String companyName, float price) {
	this.companyName = companyName;
	this.price = price;
}

public String getCompanyName() {
	return companyName;
}

public float getPrice() {
	return price;
}

}

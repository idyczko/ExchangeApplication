package com.capgemini.exchangeapp.datamodel;

import java.math.BigDecimal;

public class Record{
private String companyName;
private BigDecimal price;



public Record(String companyName, BigDecimal price) {
	this.companyName = companyName;
	this.price = price;
	this.price.setScale(2, BigDecimal.ROUND_HALF_EVEN);
}

public String getCompanyName() {
	return companyName;
}

public BigDecimal getPrice() {
	return price;
}

}

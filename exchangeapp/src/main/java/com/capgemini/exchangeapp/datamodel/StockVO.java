package com.capgemini.exchangeapp.datamodel;

import java.math.BigDecimal;

public class StockVO {
	private String companyName;
	private BigDecimal purchasePrice;
	private Integer numberOfStocks;

	public StockVO(String companyName, BigDecimal purchasePrice, Integer numberOfStocks) {
		this.companyName = companyName;
		this.purchasePrice = purchasePrice;
		this.numberOfStocks = numberOfStocks;
	}

	public String getCompanyName() {
		return companyName;
	}

	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}

	public Integer getNumberOfStocks() {
		return numberOfStocks;
	}

}

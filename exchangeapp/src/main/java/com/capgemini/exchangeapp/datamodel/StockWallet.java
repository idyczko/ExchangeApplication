package com.capgemini.exchangeapp.datamodel;

import java.math.BigDecimal;
import java.util.HashMap;

public class StockWallet {
	private HashMap<String, Integer> stock = new HashMap<String, Integer>();
	private HashMap<String, BigDecimal> purchasePrices = new HashMap<String, BigDecimal>();

	public HashMap<String, Integer> getStock() {
		return stock;
	}

	public HashMap<String, BigDecimal> getPurchasePrices() {
		return purchasePrices;
	}

	public void remove(String companyName) {
		stock.remove(companyName);
		purchasePrices.remove(companyName);
	}

	public void put(String companyName, int numberOfStock, BigDecimal purchasePrice) {
		stock.put(companyName, numberOfStock);
		purchasePrices.put(companyName, purchasePrice);
	}

}

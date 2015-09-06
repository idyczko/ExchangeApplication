package com.capgemini.exchangeapp;

import java.util.HashMap;

public class StockWallet {
	private HashMap<String, Integer> stock = new HashMap<String, Integer>();
	private HashMap<String, Float> purchasePrices = new HashMap<String, Float>();

	public HashMap<String, Integer> getStock() {
		return stock;
	}

	public HashMap<String, Float> getPurchasePrices() {
		return purchasePrices;
	}

	public void remove(String companyName) {
		stock.remove(companyName);
		purchasePrices.remove(companyName);
	}

	public void put(String companyName, int numberOfStock, float purchasePrice) {
		stock.put(companyName, numberOfStock);
		purchasePrices.put(companyName, purchasePrice);
	}

}

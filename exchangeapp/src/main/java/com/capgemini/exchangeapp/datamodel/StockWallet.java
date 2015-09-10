package com.capgemini.exchangeapp.datamodel;

import java.math.BigDecimal;
import java.util.HashMap;

public class StockWallet {
	private HashMap<String, Integer> stock = new HashMap<String, Integer>();
	private HashMap<String, BigDecimal> purchasePrices = new HashMap<String, BigDecimal>();

	public HashMap<String, Integer> getStock() {
		return stock;
	}

	public BigDecimal getPurchasePrice(String companyName) {
		return purchasePrices.get(companyName);
	}

	public void remove(String companyName) {
		stock.remove(companyName);
		purchasePrices.remove(companyName);
	}

	public void remove(String companyName, Integer stockNumber) {
		if (stock.get(companyName) > stockNumber) {
			stock.put(companyName, stock.get(companyName) - stockNumber);
		}
		if (stock.get(companyName) == stockNumber) {
			remove(companyName);
		}
	}

	public void put(String companyName, Integer numberOfStock, BigDecimal purchasePrice) {
		if (numberOfStock == 0) {
			return;
		}
		if (stock.containsKey(companyName)) {
			purchasePrices.put(companyName, calculateAveragePrice(companyName, numberOfStock, purchasePrice));
			stock.put(companyName, numberOfStock + stock.get(companyName));
			return;
		}
		stock.put(companyName, numberOfStock);
		purchasePrices.put(companyName, purchasePrice);
	}

	private BigDecimal calculateAveragePrice(String companyName, Integer numberOfStock, BigDecimal purchasePrice) {
		return (purchasePrices.get(companyName)
				.multiply(new BigDecimal(stock.get(companyName)).add(purchasePrice.add(new BigDecimal(numberOfStock)))))
						.divide(new BigDecimal(numberOfStock + stock.get(companyName)), 2, BigDecimal.ROUND_DOWN);
	}

}

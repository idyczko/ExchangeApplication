package com.capgemini.exchangeapp.datamodel;

import java.math.BigDecimal;
import java.util.HashMap;

public class StockWallet {
	private HashMap<String, Integer> stock = new HashMap<String, Integer>();
	private HashMap<String, BigDecimal> purchasePrices = new HashMap<String, BigDecimal>();

	public void remove(String companyName) {
		stock.remove(companyName);
		purchasePrices.remove(companyName);
	}

	public void remove(String companyName, Integer stockNumber) {
		if (stock.get(companyName) == stockNumber) {
			remove(companyName);
			return;
		}
		if (stock.get(companyName) > stockNumber) {
			stock.put(companyName, stock.get(companyName) - stockNumber);
			System.out.println(stock.get(companyName));
		}
	}

	private BigDecimal calculateAveragePrice(String companyName, Integer numberOfStock, BigDecimal purchasePrice) {
		Integer currentStocks = stock.get(companyName);
		BigDecimal currentPurchasePrice = purchasePrices.get(companyName);
		return ((currentPurchasePrice.multiply(new BigDecimal(currentStocks)))
				.add(purchasePrice.multiply(new BigDecimal(numberOfStock))))
						.divide(new BigDecimal(numberOfStock + currentStocks), 2, BigDecimal.ROUND_DOWN);
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

	public BigDecimal getPurchasePrice(String companyName) {
		return purchasePrices.get(companyName);
	}

	public HashMap<String, Integer> getStock() {
		return stock;
	}

}

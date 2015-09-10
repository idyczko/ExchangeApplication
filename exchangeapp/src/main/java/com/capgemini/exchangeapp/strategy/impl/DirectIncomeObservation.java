package com.capgemini.exchangeapp.strategy.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

import com.capgemini.exchangeapp.Customer;
import com.capgemini.exchangeapp.comparator.StockPriceComparator;
import com.capgemini.exchangeapp.strategy.InvestmentStrategy;

public class DirectIncomeObservation implements InvestmentStrategy {

	private BigDecimal returnRate;
	private Random random = new Random();

	public DirectIncomeObservation(BigDecimal returnRate) {
		this.returnRate = returnRate;
		this.returnRate.setScale(2, BigDecimal.ROUND_FLOOR);
	}

	public void makeNextMove(Customer customer) {
		ArrayList<String> stockToSell = findStockToSell(customer);
		for (String companyName : stockToSell) {
			customer.sellStock(companyName);
		}
		ArrayList<String> mostAttractiveStocks = customer.getBrokerageHouse().getMostAttractiveStock(
				new StockPriceComparator(), random.nextInt(customer.getBrokerageHouse().getCompaniesNumber() + 1));
		for (String companyName : mostAttractiveStocks) {
			BigDecimal cashToSpend = customer.getCashWallet().getCash()
					.divide(new BigDecimal(mostAttractiveStocks.size()), 2, BigDecimal.ROUND_FLOOR);
			customer.buyStock(companyName, cashToSpend);
		}
	}

	private ArrayList<String> findStockToSell(Customer customer) {
		ArrayList<String> stockToSell = new ArrayList<String>();
		for (String companyName : customer.getStockWallet().getStock().keySet()) {
			BigDecimal purchasePrice = customer.getStockWallet().getPurchasePrice(companyName);
			BigDecimal currentPrice = customer.getBrokerageHouse().calculateWholeTransactionCost(companyName, 1);
			if (canMakeEnoughCash(purchasePrice, currentPrice)) {
				stockToSell.add(companyName);
			}
		}
		return stockToSell;
	}

	private boolean canMakeEnoughCash(BigDecimal purchasePrice, BigDecimal currentPrice) {
		return (purchasePrice.subtract(currentPrice)).divide(purchasePrice, 4, BigDecimal.ROUND_CEILING)
				.multiply(new BigDecimal(100)).compareTo(returnRate) > 0;
	}

}

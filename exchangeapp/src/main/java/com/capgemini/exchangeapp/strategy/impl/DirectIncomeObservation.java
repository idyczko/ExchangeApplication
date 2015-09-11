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
		sellStocks(customer);
		buyStocks(customer);
	}

	private void sellStocks(Customer customer) {
		customer.sellStock(findStocksToSell(customer));
	}

	private void buyStocks(Customer customer) {
		int differentStocksToBuy = random.nextInt(customer.getBrokerageHouse().getCompaniesNumber() + 1);
		ArrayList<String> mostAttractiveStocks = customer.getBrokerageHouse()
				.getMostAttractiveStock(new StockPriceComparator(), differentStocksToBuy);
		for (String companyName : mostAttractiveStocks) {
			BigDecimal cashToSpend = customer.getCashWallet().getCash()
					.divide(new BigDecimal(mostAttractiveStocks.size()), 2, BigDecimal.ROUND_FLOOR);
			customer.buyStock(companyName, cashToSpend);
		}
	}

	private ArrayList<String> findStocksToSell(Customer customer) {
		ArrayList<String> stockToSell = new ArrayList<String>();
		for (String companyName : customer.getStockWallet().getStock().keySet()) {
			if (canMakeEnoughCash(customer, companyName)) {
				stockToSell.add(companyName);
			}
		}
		return stockToSell;
	}

	private boolean canMakeEnoughCash(Customer customer, String companyName) {
		BigDecimal purchasePrice = customer.getStockWallet().getPurchasePrice(companyName);
		BigDecimal currentPrice = customer.getBrokerageHouse().calculateWholeTransactionCost(companyName, 1);
		return (purchasePrice.subtract(currentPrice)).divide(purchasePrice, 4, BigDecimal.ROUND_CEILING)
				.multiply(new BigDecimal(100)).compareTo(returnRate) > 0;
	}

}

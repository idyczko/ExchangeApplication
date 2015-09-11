package com.capgemini.exchangeapp.strategy.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;

import com.capgemini.exchangeapp.Customer;
import com.capgemini.exchangeapp.comparator.PercentageChangeComparator;
import com.capgemini.exchangeapp.datamodel.Statistics;
import com.capgemini.exchangeapp.strategy.InvestmentStrategy;

public class PercentageChangeObservation implements InvestmentStrategy {
	private Boolean inverted = false;
	private Comparator<Statistics> comparator;

	public PercentageChangeObservation() {
		comparator = new PercentageChangeComparator(inverted);
	}

	public PercentageChangeObservation(boolean inverted) {
		this.inverted = inverted;
		comparator = new PercentageChangeComparator(inverted);
	}

	public void makeNextMove(Customer customer) {
		sellStocks(customer);
		buyStocks(customer);
	}

	private void sellStocks(Customer customer) {
		customer.sellStock(findStocksToSell(customer));
	}

	private void buyStocks(Customer customer) {
		String mostAttractiveStock = customer.getBrokerageHouse()
				.getMostAttractiveStock(comparator);
		if (customer.getBrokerageHouse().getDailyPercentageChange(mostAttractiveStock)
				.compareTo(new BigDecimal(0)) > 0) {
			customer.buyStock(mostAttractiveStock);
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
		return customer.getBrokerageHouse().getDailyPercentageChange(companyName).compareTo(new BigDecimal(0)) < 0;
	}

}

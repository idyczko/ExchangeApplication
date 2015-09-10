package com.capgemini.exchangeapp.strategy.impl;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.capgemini.exchangeapp.Customer;
import com.capgemini.exchangeapp.comparator.PercentageChangeComparator;
import com.capgemini.exchangeapp.strategy.InvestmentStrategy;

public class PercentageChangeObservation implements InvestmentStrategy {
	private Boolean inverted = false;
	
	public PercentageChangeObservation() {
	}
	
	public PercentageChangeObservation(boolean inverted) {
		this.inverted=inverted;
	}

	public void makeNextMove(Customer customer) {
		ArrayList<String> stockToSell = findStockToSell(customer);
		for (String companyName : stockToSell) {
			customer.sellStock(companyName);
		}
		String mostAttractiveStock = customer.getBrokerageHouse()
				.getMostAttractiveStock(new PercentageChangeComparator(inverted));
		if (customer.getBrokerageHouse().getDailyPercentageChange(mostAttractiveStock).compareTo(new BigDecimal(0))>0) {
			customer.buyStock(mostAttractiveStock);
		}
	}

	private ArrayList<String> findStockToSell(Customer customer) {
		ArrayList<String> stockToSell = new ArrayList<String>();
		for (String companyName : customer.getStockWallet().getStock().keySet()) {
			if (customer.getBrokerageHouse().getDailyPercentageChange(companyName).compareTo(new BigDecimal(0)) < 0) {
				stockToSell.add(companyName);
			}
		}
		return stockToSell;
	}

}

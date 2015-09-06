package com.capgemini.exchangeapp;

public class PercentageChangeObservation implements InvestmentStrategy {

	public void makeNextMove(Customer customer) {
		for (String companyName : customer.getStockWallet().getStock().keySet()) {
			if (customer.getBrokerageHouse().getDailyPercentageChange(companyName) < 0) {
				customer.sellStock(companyName);
			}
		}
		customer.buyStock(customer.getBrokerageHouse().getMostAttractiveStock(new PercentageChangeComparator()));
	}

}

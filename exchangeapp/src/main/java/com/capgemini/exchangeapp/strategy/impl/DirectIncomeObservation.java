package com.capgemini.exchangeapp.strategy.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

import com.capgemini.exchangeapp.Customer;
import com.capgemini.exchangeapp.comparator.StockPriceComparator;
import com.capgemini.exchangeapp.strategy.InvestmentStrategy;

public class DirectIncomeObservation implements InvestmentStrategy {

	private BigDecimal returnRate = new BigDecimal(0).setScale(2, BigDecimal.ROUND_FLOOR);
	private Random random = new Random();

	public DirectIncomeObservation(BigDecimal returnRate) {
		this.returnRate = returnRate;
	}

	public void makeNextMove(Customer customer) {
		ArrayList<String> stockToSell = findStockToSell(customer);
		for (String companyName : stockToSell) {
			customer.sellStock(companyName);
		}
		ArrayList<String> mostAttractiveStocks = customer.getBrokerageHouse().getMostAttractiveStock(new StockPriceComparator(), random.nextInt(customer.getBrokerageHouse().getData().size()));
		for(String companyName: mostAttractiveStocks){			
			customer.buyStock(companyName, customer.getCashWallet().getCash().divide(new BigDecimal(mostAttractiveStocks.size()), 2, BigDecimal.ROUND_FLOOR));
		}
	}

	private ArrayList<String> findStockToSell(Customer customer) {
		ArrayList<String> stockToSell = new ArrayList<String>();
		for (String companyName : customer.getStockWallet().getStock().keySet()) {
			BigDecimal purchasePrice = customer.getStockWallet().getPurchasePrice(companyName);
			BigDecimal currentPrice = customer.getBrokerageHouse().calculateWholeTransactionCost(companyName, 1);
			if ((purchasePrice.subtract(currentPrice)).divide(purchasePrice, 4, BigDecimal.ROUND_CEILING).multiply(new BigDecimal(100))
					.compareTo(returnRate) > 0) {
				stockToSell.add(companyName);
			}
		}
		return stockToSell;
	}

}

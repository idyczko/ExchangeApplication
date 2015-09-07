package com.capgemini.exchangeapp;

import java.math.BigDecimal;

import com.capgemini.exchangeapp.datamodel.CashWallet;
import com.capgemini.exchangeapp.datamodel.StockWallet;
import com.capgemini.exchangeapp.strategy.InvestmentStrategy;

public class Customer {
	private BrokerageHouse brokerageHouse;
	private CashWallet cashWallet;
	private StockWallet stockWallet;
	private InvestmentStrategy investmentStrategy;

	public Customer(BrokerageHouse brokerageHouse, CashWallet cashWallet, StockWallet stockWallet,
			InvestmentStrategy investmentStrategy) {
		this.brokerageHouse = brokerageHouse;
		this.cashWallet = cashWallet;
		this.stockWallet = stockWallet;
		this.investmentStrategy = investmentStrategy;
	}

	public Customer(BrokerageHouse brokerageHouse, BigDecimal cash, InvestmentStrategy investmentStrategy) {
		this.brokerageHouse = brokerageHouse;
		this.cashWallet = new CashWallet(cash);
		this.stockWallet = new StockWallet();
		this.investmentStrategy = investmentStrategy;
	}

	public Boolean makeNextMove() {
		investmentStrategy.makeNextMove(this);
		return brokerageHouse.loadNextDayData();
	}

	public void sellStock(String companyName) {
		BigDecimal income = brokerageHouse.sellStock(companyName, stockWallet.getStock().get(companyName));
		cashWallet.receive(income);
		if (income.compareTo(new BigDecimal(0)) > 0) {
			stockWallet.remove(companyName);
		}
	}

	public void buyStock(String companyName) {
		int numberOfStock = calculateNumberOfStock(companyName, cashWallet.getCash());
		BigDecimal cashToPay = new BigDecimal(numberOfStock).multiply(brokerageHouse.getPrice(companyName)).multiply(new BigDecimal(1).add(HelperClass.SPREAD)).setScale(2, BigDecimal.ROUND_HALF_EVEN);
		numberOfStock = brokerageHouse.buyStock(companyName, cashToPay);
		cashToPay = new BigDecimal(numberOfStock).multiply(brokerageHouse.getPrice(companyName)).multiply(new BigDecimal(1).add(HelperClass.SPREAD));
		if (numberOfStock != 0 && cashWallet.pay(cashToPay)) {
			stockWallet.put(companyName, numberOfStock, brokerageHouse.getPrice(companyName));
		}
	}

	private int calculateNumberOfStock(String companyName, BigDecimal cash) {
		return (cash.divide(brokerageHouse.getPrice(companyName).multiply(new BigDecimal(1).add(HelperClass.SPREAD)), 0,
				BigDecimal.ROUND_DOWN)).intValue();
	}

	public BrokerageHouse getBrokerageHouse() {
		return brokerageHouse;
	}

	public CashWallet getCashWallet() {
		return cashWallet;
	}

	public StockWallet getStockWallet() {
		return stockWallet;
	}

}

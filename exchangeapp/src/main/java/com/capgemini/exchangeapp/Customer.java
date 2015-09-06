package com.capgemini.exchangeapp;

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

	public void makeNextMove() {
		investmentStrategy.makeNextMove(this);
		brokerageHouse.loadNextDayData();
	}

	public void sellStock(String companyName) {
		cashWallet.receive(brokerageHouse.sellStock(companyName, stockWallet.getStock().get(companyName)));
		stockWallet.remove(companyName);
	}

	public void buyStock(String companyName) {
	int numberOfStock = brokerageHouse.buyStock(companyName, cashWallet);
	stockWallet.put(companyName, numberOfStock, brokerageHouse.getPrice(companyName));
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

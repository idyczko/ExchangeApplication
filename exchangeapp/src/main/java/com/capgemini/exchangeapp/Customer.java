package com.capgemini.exchangeapp;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.capgemini.exchangeapp.datamodel.CashWallet;
import com.capgemini.exchangeapp.datamodel.StockWallet;
import com.capgemini.exchangeapp.strategy.InvestmentStrategy;

public class Customer {
	private BrokerageHouse brokerageHouse;
	private CashWallet cashWallet;
	private StockWallet stockWallet;
	private InvestmentStrategy investmentStrategy;

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
		stockWallet.remove(companyName);
	}
	
	public void sellStock(ArrayList<String> stocks) {
		for(String companyName: stocks){
			sellStock(companyName);
		}
	}

	public void buyStock(String companyName) {
		BigDecimal cashToPay = brokerageHouse.calculateWholeTransactionCost(companyName,
				brokerageHouse.calculateStockNumber(companyName, cashWallet.getCash()));
		if (cashWallet.pay(cashToPay)) {
			Integer numberOfStock = brokerageHouse.buyStock(companyName, cashToPay);
			stockWallet.put(companyName, numberOfStock, brokerageHouse.getPrice(companyName));
		}
	}

	public void sellStock(String companyName, Integer stockNumber) {
		BigDecimal income = brokerageHouse.sellStock(companyName, stockNumber);
		cashWallet.receive(income);
		stockWallet.remove(companyName, stockNumber);
	}

	public void buyStock(String companyName, BigDecimal cash) {
		BigDecimal cashToPay = brokerageHouse.calculateWholeTransactionCost(companyName,
				brokerageHouse.calculateStockNumber(companyName, cash));
		if (cashWallet.pay(cashToPay)) {
			Integer numberOfStock = brokerageHouse.buyStock(companyName, cashToPay);
			stockWallet.put(companyName, numberOfStock, brokerageHouse.getPrice(companyName));
		}
	}

	public void getReport() {
		brokerageHouse.getReport();
		System.out.println();
		System.out.println("Customer cash: " + cashWallet.getCash());
		System.out.println("Customer stock: " + stockWallet.getStock());
		System.out.println("Broker cash: " + brokerageHouse.getHouseIncome().getCash());
		System.out.println();
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

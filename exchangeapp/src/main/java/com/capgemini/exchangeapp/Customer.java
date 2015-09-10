package com.capgemini.exchangeapp;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import com.capgemini.exchangeapp.datamodel.CashWallet;
import com.capgemini.exchangeapp.datamodel.StockWallet;
import com.capgemini.exchangeapp.strategy.InvestmentStrategy;

public class Customer {
	@Autowired
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
		getReport();
		return brokerageHouse.loadNextDayData();
	}

	public void sellStock(String companyName) {
		BigDecimal income = brokerageHouse.sellStock(companyName, stockWallet.getStock().get(companyName));
		cashWallet.receive(income);
		stockWallet.remove(companyName);
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

package com.capgemini.exchangeapp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import com.capgemini.exchangeapp.datamodel.RecordVO;
import com.capgemini.exchangeapp.datamodel.Statistics;
import com.capgemini.exchangeapp.datamodel.StockVO;

public class ViewDataProviderImpl implements ViewDataProvider {
	
	private Customer customer;

	@Override
	public BigDecimal getCustomerCash() {
		return customer.getCashWallet().getCash();
	}

	@Override
	public BigDecimal getBrokerCash() {
		return customer.getBrokerageHouse().getHouseIncome().getCash();
	}

	@Override
	public Collection<StockVO> getCustomerStocks() {
		ArrayList<StockVO> stockList = new ArrayList<StockVO>();
		for(String companyName: customer.getStockWallet().getStock().keySet()){
			BigDecimal purchasePrice = customer.getStockWallet().getPurchasePrice(companyName);
			Integer stocksNumber = customer.getStockWallet().getStock().get(companyName);
			stockList.add(new StockVO(companyName, purchasePrice, stocksNumber));
		}
		return stockList;
	}

	@Override
	public Collection<RecordVO> getExchangeStocks() {
		ArrayList<RecordVO> recordList = new ArrayList<RecordVO>();
		for(String companyName: customer.getBrokerageHouse().getData().keySet()){
			Statistics statistics = customer.getBrokerageHouse().getData().get(companyName);
			recordList.add(new RecordVO(companyName, statistics));
		}
		return recordList;
	}

	@Override
	public void buyStocks(String companyName, Integer quantity) {
		customer.buyStock(companyName, quantity);		
	}
	
	@Override
	public void sellStocks(String companyName, Integer quanitity) {
		customer.sellStock(companyName, quanitity);
	}
	
	@Override
	public void loadNextDay() {
		customer.makeNextMove();
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


}

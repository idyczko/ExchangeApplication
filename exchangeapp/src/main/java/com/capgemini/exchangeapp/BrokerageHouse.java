package com.capgemini.exchangeapp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.NoSuchElementException;

import com.capgemini.exchangeapp.datamodel.CashWallet;
import com.capgemini.exchangeapp.datamodel.Record;
import com.capgemini.exchangeapp.datamodel.Statistics;
import com.google.common.base.Functions;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.Ordering;

public class BrokerageHouse {
private ExchangeDataProvider stock;
private CashWallet houseIncome = new CashWallet(new BigDecimal(0));
private HashMap<String, Statistics> data = new HashMap<String, Statistics>();


public BrokerageHouse(ExchangeDataProvider stock) {
	this.stock = stock;
	initializeMapOnFirstDay();
}

private void initializeMapOnFirstDay() {
	ArrayList<Record> firstRecords=stock.getNextDayRecords();
	for(Record record: firstRecords){
		data.put(record.getCompanyName(), new Statistics(record.getPrice()));
		Statistics stat = data.get(record.getCompanyName());
		System.out.println(record.getCompanyName()+" "+stat.getCurrentPrice() +" "+ stat.getDailyChange()+ " "+stat.getDailyPercentageChange());
	}
}

public Boolean loadNextDayData(){	
	try{
	ArrayList<Record> newRecords=stock.getNextDayRecords();
	for(Record record: newRecords){
		data.get(record.getCompanyName()).updatePrice(record.getPrice());
		Statistics stat = data.get(record.getCompanyName());
		System.out.println(record.getCompanyName()+" "+stat.getCurrentPrice() +" "+ stat.getDailyChange()+ " "+stat.getDailyPercentageChange());
	}
	return true;
	}catch(NoSuchElementException e){
		return false;
	}
}

public String getMostAttractiveStock(Comparator<Statistics> comparator) {
	return ImmutableSortedMap.copyOf(data, Ordering.from(comparator).onResultOf(Functions.forMap(data))).firstKey();
}

public BigDecimal sellStock(String companyName, Integer stockNumber) {
	BigDecimal turnover = calculateTransactionTurnover(companyName, stockNumber);
	this.houseIncome.receive(calculateSpread(turnover));
	return turnover.subtract(calculateSpread(turnover));
}

public int buyStock(String companyName, BigDecimal cash) {
	int stockNumber = cash.divide(data.get(companyName).getCurrentPrice().multiply(new BigDecimal(1).add(HelperClass.SPREAD)), 0,
			BigDecimal.ROUND_DOWN).intValue();
	BigDecimal turnover = calculateTransactionTurnover(companyName, stockNumber);
	this.houseIncome.receive(calculateSpread(turnover));
	return stockNumber;
}

private BigDecimal calculateTransactionTurnover(String companyName, int stockNumber) {
	return data.get(companyName).getCurrentPrice().multiply(new BigDecimal(stockNumber)).setScale(2, BigDecimal.ROUND_HALF_EVEN);
}

private BigDecimal calculateSpread(BigDecimal turnover) {
	return turnover.multiply(HelperClass.SPREAD);
}

public BigDecimal getPrice(String companyName) {
	return data.get(companyName).getCurrentPrice();
}

public BigDecimal getDailyPercentageChange(String companyName) {
	return data.get(companyName).getDailyPercentageChange();
}

public BigDecimal getDailyChange(String companyName) {
	return data.get(companyName).getDailyChange();
}

public HashMap<String, Statistics> getData() {
	return data;
}

public CashWallet getHouseIncome() {
	return houseIncome;
}

}

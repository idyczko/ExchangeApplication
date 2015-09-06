package com.capgemini.exchangeapp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import com.google.common.base.Functions;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.Ordering;

public class BrokerageHouse {
private ExchangeDataProvider stock;
private CashWallet houseIncome;
private HashMap<String, Statistics> data = new HashMap<String, Statistics>();


public BrokerageHouse(ExchangeDataProvider stock) {
	this.stock = stock;
	initializeMapOnFirstDay();
}

private void initializeMapOnFirstDay() {
	ArrayList<Record> firstRecords=stock.getNextDayRecords();
	for(Record record: firstRecords){
		data.put(record.getCompanyName(), new Statistics(record.getPrice()));
	}
}

public void loadNextDayData(){	
	ArrayList<Record> newRecords=stock.getNextDayRecords();
	for(Record record: newRecords){
		data.get(record.getCompanyName()).updatePrice(record.getPrice());
		Statistics stat = data.get(record.getCompanyName());
		System.out.println(record.getCompanyName()+" "+stat.getCurrentPrice() +" "+ stat.getDailyChange()+ " "+stat.getDailyPercentageChange());
	}
}

public HashMap<String, Statistics> getData() {
	return data;
}

public float sellStock(String companyName, Integer stockNumber) {
	float income = data.get(companyName).getCurrentPrice()*stockNumber;
	this.houseIncome.receive(income*HelperClass.SPREAD);
	return income-income*HelperClass.SPREAD;
}

public String getMostAttractiveStock(Comparator<Statistics> comparator) {
	return ImmutableSortedMap.copyOf(data, Ordering.from(comparator).onResultOf(Functions.forMap(data))).firstKey();
}

public Float getPrice(String companyName) {
	return data.get(companyName).getCurrentPrice();
}

public double getDailyPercentageChange(String companyName) {
	return data.get(companyName).getDailyPercentageChange();
}

public int buyStock(String companyName, CashWallet cashWallet) {
	// TODO Auto-generated method stub
	return 0;
}

}

package com.capgemini.exchangeapp;

import java.util.ArrayList;
import java.util.HashMap;

public class BrokerageHouse {
private final float SPREAD = 0.5f;
private ExchangeDataProvider stock;
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

}

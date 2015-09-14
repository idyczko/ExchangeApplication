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
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Ordering;

public class BrokerageHouse {
	private ExchangeDataProvider exchangeDataProvider;
	private CashWallet houseIncome = new CashWallet(new BigDecimal(0));
	private HashMap<String, Statistics> data = new HashMap<String, Statistics>();

	public BrokerageHouse(ExchangeDataProvider stock) {
		this.exchangeDataProvider = stock;
		initializeMapOnFirstDay();
	}

	private Boolean initializeMapOnFirstDay() {
		try {
			ArrayList<Record> firstRecords = exchangeDataProvider.getNextDayRecords();
			for (Record record : firstRecords) {
				data.put(record.getCompanyName(), new Statistics(record.getPrice()));
			}
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public Boolean loadNextDayData() {
		try {
			ArrayList<Record> newRecords = exchangeDataProvider.getNextDayRecords();
			for (Record record : newRecords) {
				data.get(record.getCompanyName()).updatePrice(record.getPrice());
			}
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public String getMostAttractiveStock(Comparator<Statistics> comparator) {
		return ImmutableSortedMap.copyOf(data, Ordering.from(comparator).onResultOf(Functions.forMap(data))).firstKey();
	}

	public ArrayList<String> getMostAttractiveStock(Comparator<Statistics> comparator, int numberOfCompanies) {
		int stocksFound = 0;
		ArrayList<String> mostAttractiveStocks = new ArrayList<String>();
		ImmutableSortedSet<String> sortedKeySet = ImmutableSortedMap
				.copyOf(data, Ordering.from(comparator).onResultOf(Functions.forMap(data))).keySet();
		for (String companyName : sortedKeySet) {
			mostAttractiveStocks.add(mostAttractiveStocks.size(), companyName);
			stocksFound++;
			if (stocksFound >= numberOfCompanies || stocksFound >= sortedKeySet.size()) {
				break;
			}
		}
		return mostAttractiveStocks;
	}

	public BigDecimal sellStock(String companyName, Integer stockNumber) {
		BigDecimal turnover = calculateTransactionTurnover(companyName, stockNumber);
		BigDecimal spread = calculateSpread(turnover);
		this.houseIncome.receive(spread);
		return turnover.subtract(spread);
	}

	public Integer buyStock(String companyName, BigDecimal cash) {
		Integer stockNumber = calculateStockNumber(companyName, cash);
		BigDecimal turnover = calculateTransactionTurnover(companyName, stockNumber);
		this.houseIncome.receive(calculateSpread(turnover));
		return stockNumber;
	}

	public BigDecimal calculateWholeTransactionCost(String companyName, Integer stockNumber) {
		BigDecimal beforeSpread = calculateTransactionTurnover(companyName, stockNumber);
		return addSpread(beforeSpread).setScale(2, BigDecimal.ROUND_CEILING);
	}

	public Integer calculateStockNumber(String companyName, BigDecimal cash) {
		return cash.divide(addSpread(data.get(companyName).getCurrentPrice()), 0, BigDecimal.ROUND_FLOOR).intValue();
	}

	private BigDecimal calculateTransactionTurnover(String companyName, Integer stockNumber) {
		return data.get(companyName).getCurrentPrice().multiply(new BigDecimal(stockNumber));
	}

	private BigDecimal addSpread(BigDecimal turnover) {
		return turnover.multiply(new BigDecimal(1).add(HelperClass.SPREAD));
	}

	private BigDecimal calculateSpread(BigDecimal turnover) {
		return turnover.multiply(HelperClass.SPREAD).setScale(2, BigDecimal.ROUND_CEILING);
	}

	private void loadData(ArrayList<Record> firstRecords) {
		for (Record record : firstRecords) {
			data.put(record.getCompanyName(), new Statistics(record.getPrice()));
		}
	}

	public void getReport() {
		for (String companyName : data.keySet()) {
			Statistics statistics = data.get(companyName);
			System.out.println(companyName + " " + statistics.getCurrentPrice() + " " + statistics.getDailyChange()
					+ " " + statistics.getDailyPercentageChange());
		}
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

	public CashWallet getHouseIncome() {
		return houseIncome;
	}

	public int getCompaniesNumber() {
		return data.size();
	}

}

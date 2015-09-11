package com.capgemini.exchangeapp.datamodel;

import java.math.BigDecimal;

public class Statistics {
	private BigDecimal currentPrice;
	private BigDecimal dailyChange;
	private BigDecimal dailyPercentageChange;

	public Statistics(BigDecimal currentPrice) {
		this.currentPrice = currentPrice;
		this.dailyPercentageChange = new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_EVEN);
		this.dailyChange = new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}
	
	public Statistics(Statistics statistics) {
		this.currentPrice = statistics.currentPrice;
		this.dailyChange = statistics.dailyChange;
		this.dailyPercentageChange = statistics.getDailyPercentageChange();
	}

	public void updatePrice(BigDecimal newPrice) {
		this.dailyPercentageChange = newPrice.subtract(currentPrice).divide(currentPrice, 4, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100)).setScale(2);
		this.dailyChange = newPrice.subtract(currentPrice);
		this.currentPrice = newPrice;
	}

	public BigDecimal getCurrentPrice() {
		return currentPrice;
	}

	public BigDecimal getDailyChange() {
		return dailyChange;
	}

	public BigDecimal getDailyPercentageChange() {
		return dailyPercentageChange;
	}

}

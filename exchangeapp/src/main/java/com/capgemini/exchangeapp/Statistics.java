package com.capgemini.exchangeapp;

public class Statistics {
	private float currentPrice;
	private float dailyChange;
	private double dailyPercentageChange;

	public Statistics(float currentPrice) {
		this.currentPrice = currentPrice;
		this.dailyPercentageChange = 0;
		this.dailyChange = 0;
	}

	public void updatePrice(Float newPrice) {
		this.dailyPercentageChange = (newPrice - currentPrice) / currentPrice * 100;
		this.dailyChange = newPrice - currentPrice;
		this.currentPrice = newPrice;
	}

	public float getCurrentPrice() {
		return currentPrice;
	}

	public float getDailyChange() {
		return dailyChange;
	}

	public double getDailyPercentageChange() {
		return dailyPercentageChange;
	}

}

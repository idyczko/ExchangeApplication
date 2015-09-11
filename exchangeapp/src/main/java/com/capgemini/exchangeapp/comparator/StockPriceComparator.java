package com.capgemini.exchangeapp.comparator;

import java.util.Comparator;

import com.capgemini.exchangeapp.datamodel.Statistics;

public class StockPriceComparator implements Comparator<Statistics> {
	private Boolean inverted = false;

	public StockPriceComparator() {
	}

	public StockPriceComparator(Boolean inverted) {
		this.inverted = inverted;
	}

	public int compare(Statistics s1, Statistics s2) {
		if (s1.getCurrentPrice().compareTo(s2.getCurrentPrice()) > 0) {
			return inverted ? -1 : 1;
		}
		return inverted ? 1 : -1;
	}

}

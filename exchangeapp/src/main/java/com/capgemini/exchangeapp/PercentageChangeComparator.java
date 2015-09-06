package com.capgemini.exchangeapp;

import java.util.Comparator;

public class PercentageChangeComparator implements Comparator<Statistics> {

	public int compare(Statistics s1, Statistics s2) {
		return (int) (s1.getDailyPercentageChange()-s2.getDailyPercentageChange());
	} 
}

package com.capgemini.exchangeapp.comparator;

import java.util.Comparator;

import com.capgemini.exchangeapp.datamodel.Statistics;

public class PercentageChangeComparator implements Comparator<Statistics> {

	public int compare(Statistics s1, Statistics s2) {
		if(s1.getDailyPercentageChange().compareTo(s2.getDailyPercentageChange())>0){
		return -1;
		}
		return 1;
	} 
}

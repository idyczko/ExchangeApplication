package com.capgemini.exchangeapp;

import org.springframework.util.comparator.ComparableComparator;

public interface InvestmentStrategy {
	public void makeNextMove(Customer customer);
}

package com.capgemini.exchangeapp.strategy;

import com.capgemini.exchangeapp.Customer;

public interface InvestmentStrategy {

	public void makeNextMove(Customer customer);

}
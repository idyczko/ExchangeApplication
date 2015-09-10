package com.capgemini.exchangeapp;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;

import com.capgemini.exchangeapp.strategy.impl.DirectIncomeObservation;

public class App {

	public static void main(String[] args) throws IOException, ParseException {
		ExchangeDataProvider dataProvider = new ExchangeDataProvider(new File(HelperClass.FILE_PATH));
		BrokerageHouse brokerageHouse = new BrokerageHouse(dataProvider);
		Customer customer = new Customer(brokerageHouse, HelperClass.INITIAL_CASH,
				new DirectIncomeObservation(new BigDecimal(0.01)));

		customer.getReport();
		while (customer.makeNextMove()) {
		}
		ArrayList<String> list = new ArrayList<String>();

		for (String company : customer.getStockWallet().getStock().keySet()) {
			list.add(company);
		}
		for (String company : list) {
			customer.sellStock(company);
		}

		customer.getReport();
	}
}

package com.capgemini.exchangeapp.main;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;

import com.capgemini.exchangeapp.BrokerageHouse;
import com.capgemini.exchangeapp.Customer;
import com.capgemini.exchangeapp.ExchangeDataProvider;
import com.capgemini.exchangeapp.HelperClass;
import com.capgemini.exchangeapp.exception.RecordParsingException;
import com.capgemini.exchangeapp.strategy.impl.DirectIncomeObservation;

public class App {

	public static void main(String[] args) throws IOException, ParseException, RecordParsingException {
		ExchangeDataProvider dataProvider = new ExchangeDataProvider(new File(HelperClass.FILE_PATH));
		BrokerageHouse brokerageHouse = new BrokerageHouse(dataProvider);
		Customer customer = new Customer(brokerageHouse, HelperClass.INITIAL_CASH,
				new DirectIncomeObservation(new BigDecimal("0.01")));

		while (customer.makeNextMove()) {
			customer.getReport();
		}

		customer.sellStock(new ArrayList<String>(customer.getStockWallet().getStock().keySet()));

		customer.getReport();
	}
}

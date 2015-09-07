package com.capgemini.exchangeapp;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.capgemini.exchangeapp.strategy.impl.PercentageChangeObservation;

@Service
public class App {

	public static void main(String[] args) throws IOException {
		ExchangeDataProvider dataProvider = new ExchangeDataProvider(new File(HelperClass.FILE_PATH));
		BrokerageHouse brokerageHouse = new BrokerageHouse(dataProvider);
		Customer customer = new Customer(brokerageHouse, HelperClass.INITIAL_CASH, new PercentageChangeObservation());
		
		System.out.println(customer.getCashWallet().getCash());
		System.out.println(customer.getStockWallet().getStock());
	while(customer.makeNextMove()){
		
		System.out.println(customer.getCashWallet().getCash());
		System.out.println(customer.getStockWallet().getStock());
	}
	ArrayList<String> list = new ArrayList<String>();
	
	for(String company: customer.getStockWallet().getStock().keySet()){
		list.add(company);
	}
	for(String company: list){
		customer.sellStock(company);
	}

		System.out.println(customer.getCashWallet().getCash());
		System.out.println(customer.getBrokerageHouse().getHouseIncome().getCash());
		/*customer.getCashWallet().pay(new BigDecimal(999.989341241));
		System.out.println(customer.getStockWallet().getStock());
		System.out.println(customer.getCashWallet().getCash());

		System.out.println(customer.getCashWallet().getCash());
		System.out.println(customer.getBrokerageHouse().getData().get("PKOBP").getCurrentPrice());
		customer.buyStock("PKOBP");
		System.out.println(customer.getStockWallet().getStock());
		System.out.println(customer.getCashWallet().getCash());*/
	}
}

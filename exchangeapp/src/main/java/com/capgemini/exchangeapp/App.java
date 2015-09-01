package com.capgemini.exchangeapp;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;

@Service
public class App {

	public static void main(String[] args) throws IOException {
		ExchangeDataProvider dataProvider = new ExchangeDataProvider(new File("C:\\Users\\idyczko\\Desktop\\Igor\\ZadanieGielda\\dane.csv"));
		BrokerageHouse brokerageHouse = new BrokerageHouse(dataProvider);
		brokerageHouse.loadNextDayData();
		brokerageHouse.loadNextDayData();
		brokerageHouse.loadNextDayData();
		brokerageHouse.loadNextDayData();
		brokerageHouse.loadNextDayData();
	}
}

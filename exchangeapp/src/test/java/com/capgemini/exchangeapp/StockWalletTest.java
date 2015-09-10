package com.capgemini.exchangeapp;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.capgemini.exchangeapp.datamodel.StockWallet;

public class StockWalletTest {
	private StockWallet stockWallet;
	private static String companyName = "MICROSOFT";
	
	@Before
	public void setUp(){
		stockWallet = new StockWallet();
	}

	@Test
	public void shouldNotPutEverythingIfZeroStocks() {
		stockWallet.put(companyName, 0, new BigDecimal("12.24"));
		assertEquals(0, stockWallet.getStock().size());
	}
	
	@Test
	public void shouldPutEverythingIf10Stocks() {
		stockWallet.put(companyName, 10, new BigDecimal("12.24"));
		assertEquals(1, stockWallet.getStock().size());
	}
	
	@Test
	public void shouldAddIfStocksExistInWallet() {
		stockWallet.put(companyName, 10, new BigDecimal("12.00"));
		stockWallet.put(companyName, 10, new BigDecimal("24.00"));
		assertEquals(20, stockWallet.getStock().get(companyName).intValue());
	}
	
	@Test
	public void shouldCorrectlyCalculateAveragePurchasePrice() {
		stockWallet.put(companyName, 10, new BigDecimal("12.00"));
		stockWallet.put(companyName, 10, new BigDecimal("24.00"));
		assertEquals(new BigDecimal("18.00"), stockWallet.getPurchasePrice(companyName));
	}
	
	@Test
	public void shouldRemoveAllStocksFromWallet() {
		stockWallet.put(companyName, 10, new BigDecimal("12.00"));
		stockWallet.put(companyName, 10, new BigDecimal("24.00"));
		stockWallet.remove(companyName);
		assertEquals(0, stockWallet.getStock().size());
	}
	
	@Test
	public void shouldRemove10StocksFromWallet() {
		stockWallet.put(companyName, 10, new BigDecimal("12.00"));
		stockWallet.put(companyName, 10, new BigDecimal("24.00"));
		stockWallet.remove(companyName, 10);
		assertEquals(10, stockWallet.getStock().get(companyName).intValue());
	}

}

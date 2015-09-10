package com.capgemini.exchangeapp;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.capgemini.exchangeapp.datamodel.CashWallet;

public class CashWalletTest{
	private CashWallet cashWallet;
	
	@Before
	public void setUp(){
		cashWallet = new CashWallet(new BigDecimal("1000.00"));
	}
	
	@Test
	public void shouldReturnFalseIfNotEnoughMoney(){
		assertFalse(cashWallet.pay(new BigDecimal("2000.00")));
	}
	
	@Test
	public void shouldNotPayIfNotEnoughMoney(){
		cashWallet.pay(new BigDecimal(2000));
		assertEquals(new BigDecimal("1000.00"), cashWallet.getCash());
	}
	
	@Test
	public void shouldReturnTrueIfEnoughMoney(){
		assertTrue(cashWallet.pay(new BigDecimal(500)));
	}
	
	@Test
	public void shouldPayIfEnoughMoney(){
		cashWallet.pay(new BigDecimal(500));
		assertEquals(new BigDecimal("500.00"), cashWallet.getCash());
	}
	
	@Test
	public void shouldReceiveMoney(){
		cashWallet.receive(new BigDecimal(1000));
		assertEquals(new BigDecimal("2000.00"), cashWallet.getCash());
	}
}

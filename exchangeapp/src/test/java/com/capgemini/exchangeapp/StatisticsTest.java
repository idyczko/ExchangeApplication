package com.capgemini.exchangeapp;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.capgemini.exchangeapp.datamodel.Statistics;

public class StatisticsTest {
	private Statistics statistics;
	
	@Before
	public void setUp(){
		statistics = new Statistics(new BigDecimal("10.24"));
	}

	@Test
	public void shouldUpdatePrice() {
		statistics.updatePrice(new BigDecimal("20.48"));
		assertEquals(new BigDecimal("20.48"), statistics.getCurrentPrice());
	}
	
	@Test
	public void shouldUpdateChange() {
		statistics.updatePrice(new BigDecimal("20.48"));
		assertEquals(new BigDecimal("10.24"), statistics.getDailyChange());
	}
	
	@Test
	public void shouldUpdatePercentageChange() {
		statistics.updatePrice(new BigDecimal("20.48"));
		assertEquals(new BigDecimal("100.00"), statistics.getDailyPercentageChange());
	}
	

}

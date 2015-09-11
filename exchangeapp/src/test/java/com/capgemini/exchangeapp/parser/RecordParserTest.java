package com.capgemini.exchangeapp.parser;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.capgemini.exchangeapp.datamodel.Record;
import com.capgemini.exchangeapp.exception.RecordParsingException;

public class RecordParserTest {
	private Record record;

	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Before
	public void setUp() throws RecordParsingException{
		record  = RecordDataParser.parse("MICROSOFT,20131212,15.25");
	}
	@Test
	public void shouldCreateRecordForMicrosoftCompanyName() {
		assertEquals("MICROSOFT", record.getCompanyName());
	}

	@Test
	public void shouldCreateRecordForCompanyPrice() {
		assertEquals(new BigDecimal("15.25"), record.getPrice());
	}
	
	@Test(expected = RecordParsingException.class)
	public void shouldCauseRecordParsingExceptionForWrongCSVLine() throws RecordParsingException {
		Record wrongRecord = RecordDataParser.parse("MICROSOFT,12.25");
	}

}

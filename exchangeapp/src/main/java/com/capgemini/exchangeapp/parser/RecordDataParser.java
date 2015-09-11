package com.capgemini.exchangeapp.parser;

import java.math.BigDecimal;

import com.capgemini.exchangeapp.datamodel.Record;
import com.capgemini.exchangeapp.exception.RecordParsingException;

public class RecordDataParser {
	public static Record parse(String csvLine) throws RecordParsingException{
		String[] recordTab = csvLine.split(",");
		if(recordTab.length!=3){
			throw new RecordParsingException();
		}
		return new Record(recordTab[0], new BigDecimal(recordTab[2]).setScale(2, BigDecimal.ROUND_HALF_EVEN));
	}
}

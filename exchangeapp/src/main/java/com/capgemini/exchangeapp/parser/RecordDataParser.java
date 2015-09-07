package com.capgemini.exchangeapp.parser;

import java.math.BigDecimal;

import com.capgemini.exchangeapp.datamodel.Record;

public class RecordDataParser {
public static Record parse(String csvLine){
	String[] recordTab = csvLine.split(",");
	return new Record(recordTab[0], new BigDecimal(recordTab[2]).setScale(2, BigDecimal.ROUND_HALF_EVEN));
}
}

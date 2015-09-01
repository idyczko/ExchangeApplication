package com.capgemini.exchangeapp;

public class RecordDataParser {
public static Record parse(String csvLine){
	String[] recordTab = csvLine.split(",");
	return new Record(recordTab[0], Float.parseFloat(recordTab[2]));
}
}

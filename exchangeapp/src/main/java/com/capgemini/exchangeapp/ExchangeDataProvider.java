package com.capgemini.exchangeapp;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

import com.capgemini.exchangeapp.datamodel.Record;
import com.capgemini.exchangeapp.exception.RecordParsingException;
import com.capgemini.exchangeapp.filereader.CustomFileReader;

public class ExchangeDataProvider {
	private TreeMap<Date, ArrayList<Record>> records = new TreeMap<Date, ArrayList<Record>>();

	public ExchangeDataProvider(File file) throws IOException, ParseException, RecordParsingException {
		records = CustomFileReader.readFile(file);
	}
	
	public ArrayList<Record> getNextDayRecords(){
		Date key = records.firstKey();
		ArrayList<Record> recordsList = records.get(key);
		records.remove(key);
		return recordsList;
	}
	
}

package com.capgemini.exchangeapp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import com.capgemini.exchangeapp.datamodel.Record;
import com.capgemini.exchangeapp.filereader.CustomFileReader;

public class ExchangeDataProvider {
	private TreeMap<Long, ArrayList<Record>> records = new TreeMap<Long, ArrayList<Record>>();

	public ExchangeDataProvider(File file) throws IOException {
		records = CustomFileReader.readFile(file);
	}
	
	public ArrayList<Record> getNextDayRecords(){
		Long key = records.firstKey();
		ArrayList<Record> recordsList = records.get(key);
		records.remove(key);
		return recordsList;
	}
	
}

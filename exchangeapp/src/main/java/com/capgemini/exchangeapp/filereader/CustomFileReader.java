package com.capgemini.exchangeapp.filereader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

import com.capgemini.exchangeapp.datamodel.Record;
import com.capgemini.exchangeapp.parser.RecordDataParser;

public class CustomFileReader {
	public static TreeMap<Long, ArrayList<Record>> readFile(File file) throws IOException {
		TreeMap<Long, ArrayList<Record>> records = new TreeMap<Long, ArrayList<Record>>();
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line;
		while ((line = reader.readLine()) != null) {
			Record recordToInsert = RecordDataParser.parse(line);
			Long key =Long.parseLong(line.split(",")[1]);
			if(records.containsKey(key)){
				records.get(key).add(recordToInsert);
				continue;
			}
			records.put(key, new ArrayList<Record>(Arrays.asList(recordToInsert)));
		}
		reader.close();
		return records;
	}
}

package com.capgemini.exchangeapp.datamodel;

import java.util.ArrayList;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

public class DataModel {
	private final StringProperty customerCash = new SimpleStringProperty();
	
	private final StringProperty stocksToBuyNumber = new SimpleStringProperty();
	
	private final StringProperty stocksToSellNumber = new SimpleStringProperty();
	
	private final ListProperty<RecordVO> recordsList = new SimpleListProperty<RecordVO>(
			FXCollections.observableList(new ArrayList<RecordVO>()));

	private final ListProperty<StockVO> customerStocksList = new SimpleListProperty<StockVO>(
			FXCollections.observableList(new ArrayList<StockVO>()));
	
	
}

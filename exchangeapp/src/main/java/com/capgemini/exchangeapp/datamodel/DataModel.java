package com.capgemini.exchangeapp.datamodel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

public class DataModel {
	private final StringProperty customerCash = new SimpleStringProperty();

	private final StringProperty brokerCash = new SimpleStringProperty();

	private final StringProperty companyToBuy = new SimpleStringProperty();
	
	private final StringProperty companyToSell = new SimpleStringProperty();
	
	private final StringProperty stocksToBuyNumber = new SimpleStringProperty();

	private final StringProperty stocksToSellNumber = new SimpleStringProperty();

	private final ListProperty<RecordVO> recordsList = new SimpleListProperty<RecordVO>(
			FXCollections.observableList(new ArrayList<RecordVO>()));

	private final ListProperty<StockVO> customerStocksList = new SimpleListProperty<StockVO>(
			FXCollections.observableList(new ArrayList<StockVO>()));

	public final void setCustomerCash(BigDecimal cash) {
		customerCash.set(cash.toString());
	}
	
	public final void setBrokerCash(BigDecimal cash) {
		brokerCash.set(cash.toString());
	}
	
	public final void setCompanyToBuy(String companyName) {
		companyToBuy.set(companyName);
	}
	
	public final void setCompanytoSell(String companyName) {
		companyToSell.set(companyName);
	}
	
	public final void setStocksToBuyNumber(Integer number) {
		stocksToBuyNumber.set(number.toString());
	}

	public final void setStocksToSellNumber(Integer number) {
		stocksToSellNumber.set(number.toString());
	}

	public final void setRecordsList(Collection<RecordVO> collection) {
		 recordsList.setAll(collection);
	}

	public final void setCustomerStocksList(Collection<StockVO> collection) {
		 customerStocksList.setAll(collection);
	}

	public final String getCustomerCash() {
		return customerCash.get();
	}
	
	public final String getBrokerCash() {
		return brokerCash.get();
	}
	
	public final String getCompanyToBuy() {
		return companyToBuy.get();
	}
	
	public final String getCompanyToSell() {
		return companyToSell.get();
	}

	public final String getStocksToBuyNumber() {
		return stocksToBuyNumber.get();
	}

	public final String getStocksToSellNumber() {
		return stocksToSellNumber.get();
	}

	public final List<RecordVO> getRecordsList() {
		return recordsList.get();
	}

	public final List<StockVO> getCustomerStocksList() {
		return customerStocksList.get();
	}

	public final StringProperty customerCashProperty() {
		return customerCash;
	}
	
	public final StringProperty companyToBuyProperty() {
		return companyToBuy;
	}
	
	public final StringProperty companyToSellProperty() {
		return companyToSell;
	}
	
	public final StringProperty brokerCashProperty() {
		return brokerCash;
	}

	public final StringProperty stocksToBuyNumberProperty() {
		return stocksToBuyNumber;
	}

	public final StringProperty stocksToSellNumberProperty() {
		return stocksToSellNumber;
	}

	public final ListProperty<RecordVO> recordsListProperty() {
		return recordsList;
	}

	public final ListProperty<StockVO> customerStocksListProperty() {
		return customerStocksList;
	}

}

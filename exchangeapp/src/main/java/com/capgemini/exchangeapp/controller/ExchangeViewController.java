package com.capgemini.exchangeapp.controller;

import com.capgemini.exchangeapp.ViewDataProvider;
import com.capgemini.exchangeapp.datamodel.DataModel;
import com.capgemini.exchangeapp.datamodel.RecordVO;
import com.capgemini.exchangeapp.datamodel.StockVO;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ExchangeViewController {
	@FXML
	private Button buyButton;
	
	@FXML
	private Button sellButton;
	
	@FXML
	private Button nextDayButton;
	
	@FXML
	private TextField stocksToBuy;

	@FXML
	private TextField stocksToSell;
	
	@FXML
	private Label customerCash;
	
	@FXML
	private Label selectedBuyCompany;
	
	@FXML
	private Label selectedSellCompany;
	
	@FXML
	private ComboBox<String> companiesToBuy;
	
	@FXML
	private ComboBox<String> companiesToSell;
	
	@FXML
	private TableView<RecordVO> recordTable;

	@FXML
	private TableColumn<RecordVO, String> companyNameColumn;

	@FXML
	private TableColumn<RecordVO, String> stockPriceColumn;
	
	@FXML
	private TableColumn<RecordVO, String> percentageChangeColumn;
	
	@FXML
	private TableColumn<RecordVO, String> changeColumn;
	
	@FXML
	private TableView<StockVO> customerStockTable;
	
	@FXML
	private TableColumn<StockVO, String> customerCompanyNameColumn;
	
	@FXML
	private TableColumn<StockVO, String> purchasePriceColumn;
	
	@FXML
	private TableColumn<StockVO, String> stocksNumberColumn;

	private final ViewDataProvider dataProvider = ViewDataProvider.INSTANCE;
	private final DataModel model = new DataModel();
	
	@FXML
	private void buyStock(ActionEvent event) {
		dataProvider.buyStocks(model.getCompanyToBuy(), Integer.parseInt(model.getStocksToBuyNumber()));
		updateData();
	}

	@FXML
	private void loadNextDay(ActionEvent event) {
		dataProvider.loadNextDay();
		updateData();
	}

	private void updateData() {
		model.setCustomerStocksList(dataProvider.getCustomerStocks());
		model.setCustomerCash(dataProvider.getCustomerCash());
		model.setBrokerCash(dataProvider.getBrokerCash());
		model.setRecordsList(dataProvider.getExchangeStocks());
	}
	
	@FXML
	private void initialize() {
		companyNameColumn.setCellValueFactory(cellData -> {
			return new SimpleStringProperty(cellData.getValue().getCompanyName());
		});
		stockPriceColumn.setCellValueFactory(cellData -> {
			return new SimpleStringProperty(cellData.getValue().getCurrentPrice().toString());
		});
		percentageChangeColumn.setCellValueFactory(cellData -> {
			return new SimpleStringProperty(cellData.getValue().getDailyPercentageChange().toString());
		});
		changeColumn.setCellValueFactory(cellData -> {
			return new SimpleStringProperty(cellData.getValue().getDailyChange().toString());
		});
		customerCompanyNameColumn.setCellValueFactory(cellData -> {
			return new SimpleStringProperty(cellData.getValue().getCompanyName());
		});
		purchasePriceColumn.setCellValueFactory(cellData -> {
			return new SimpleStringProperty(cellData.getValue().getPurchasePrice().toString());
		});
		stocksNumberColumn.setCellValueFactory(cellData -> {
			return new SimpleStringProperty(cellData.getValue().getNumberOfStocks().toString());
		});
		companiesToBuy.getItems().add("Fruit");
		companiesToSell.getItems().add("Fruit");
		
		recordTable.itemsProperty().bind(model.recordsListProperty());
		customerStockTable.itemsProperty().bind(model.customerStocksListProperty());
		
		customerCash.textProperty().bind(model.customerCashProperty());
		stocksToBuy.textProperty().bindBidirectional(model.stocksToBuyNumberProperty());
		stocksToSell.textProperty().bindBidirectional(model.stocksToSellNumberProperty());
		model.companyToBuyProperty().bind(companiesToBuy.getSelectionModel().selectedItemProperty());
		model.companyToSellProperty().bind(companiesToSell.getSelectionModel().selectedItemProperty());
		
	}

	public ViewDataProvider getDataProvider() {
		return dataProvider;
	}

}

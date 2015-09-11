package com.capgemini.exchangeapp.controller;

import com.capgemini.exchangeapp.datamodel.RecordVO;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ExchangeViewController {
	@FXML
	private TextField stocksToBuy;

	@FXML
	private TextField stocksToSell;
	
	@FXML
	private ComboBox<String> companiesToBuy;
	
	@FXML
	private ComboBox<String> companiesToSell;
	
	@FXML
	private TableView<RecordVO> recordTable;

	@FXML
	private TableColumn<RecordVO, String> companyName;

	@FXML
	private TableColumn<RecordVO, String> stockPrice;
	
	@FXML
	private TableColumn<RecordVO, String> percentageChange;
	
	@FXML
	private TableColumn<RecordVO, String> change;

	
	@FXML
	private void initialize() {

	}

}

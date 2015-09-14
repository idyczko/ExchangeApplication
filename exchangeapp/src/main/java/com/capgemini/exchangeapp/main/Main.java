package com.capgemini.exchangeapp.main;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import com.capgemini.exchangeapp.BrokerageHouse;
import com.capgemini.exchangeapp.Customer;
import com.capgemini.exchangeapp.ExchangeDataProvider;
import com.capgemini.exchangeapp.HelperClass;
import com.capgemini.exchangeapp.controller.ExchangeViewController;
import com.capgemini.exchangeapp.exception.RecordParsingException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	 private Stage primaryStage;
	 private ExchangeDataProvider exchangeDataProvider;
	 private BrokerageHouse brokerageHouse;
	 private Customer customer;
	 
	    @Override
	    public void start(Stage primaryStage) throws IOException, ParseException, RecordParsingException{
	    	exchangeDataProvider = new ExchangeDataProvider(new File(HelperClass.FILE_PATH));
			brokerageHouse = new BrokerageHouse(exchangeDataProvider);
			customer = new Customer(brokerageHouse, HelperClass.INITIAL_CASH);
	        this.primaryStage = primaryStage;
	        this.primaryStage.setTitle("Exchange Service");

	        initExchangeLayout();
	    }


	    public void initExchangeLayout() {
	        try {
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(Main.class.getResource("/com/capgemini/exchangeapp/view/exchangeappview.fxml"));
	            BorderPane exchangeLayout = (BorderPane) loader.load();

	            ExchangeViewController exchangeViewController= loader.getController();
	            exchangeViewController.getDataProvider().setCustomer(customer);

	            Scene scene = new Scene(exchangeLayout);
	            scene.getStylesheets()
				.add(getClass().getResource("/com/capgemini/exchangeapp/view/stylesheet.css").toExternalForm());
	            
	            primaryStage.setScene(scene);
	            primaryStage.show();
	            
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public Stage getPrimaryStage() {
	        return primaryStage;
	    }

	    public static void main(String[] args) {
	        launch(args);
	    }
}

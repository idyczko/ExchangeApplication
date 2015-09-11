package com.capgemini.exchangeapp.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	 private Stage primaryStage;
	 
	    @Override
	    public void start(Stage primaryStage){
	        this.primaryStage = primaryStage;
	        this.primaryStage.setTitle("Book Service");

	        initExchangeLayout();
	    }


	    public void initExchangeLayout() {
	        try {
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(Main.class.getResource("view/exchangeappview.fxml"));
	            BorderPane exchangeLayout = (BorderPane) loader.load();

	            Scene scene = new Scene(exchangeLayout);
	            scene.getStylesheets()
				.add(getClass().getResource("view/stylesheet.css").toExternalForm());
	            
	            
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

package com.capgemini.exchangeapp;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Set;

import com.capgemini.exchangeapp.datamodel.RecordVO;
import com.capgemini.exchangeapp.datamodel.StockVO;

public interface ViewDataProvider {

	ViewDataProvider INSTANCE = new ViewDataProviderImpl();

	BigDecimal getCustomerCash();

	BigDecimal getBrokerCash();
	
	void buyStocks(String companyName, Integer quanitity);

	void sellStocks(String companyName, Integer quanitity);
	
	void loadNextDay();

	Collection<StockVO> getCustomerStocks();

	Collection<RecordVO> getExchangeStocks();
	
	void setCustomer(Customer customer);

	Set<String> getCompanies();

	Set<String> getCustomersCompanyNames();

}

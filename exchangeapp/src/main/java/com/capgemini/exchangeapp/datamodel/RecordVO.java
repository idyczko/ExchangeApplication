package com.capgemini.exchangeapp.datamodel;

public class RecordVO extends Statistics {
	String companyName;

	public RecordVO(String companyName, Statistics statistics) {
		super(statistics);
		this.companyName = companyName;
	}

	public String getCompanyName() {
		return companyName;
	}
}

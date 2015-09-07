package com.capgemini.exchangeapp.datamodel;

import java.math.BigDecimal;

public class CashWallet {
	public BigDecimal cash;

	public CashWallet(BigDecimal cash) {
		this.cash = cash;
		this.cash.setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}

	public void receive(BigDecimal cash){
		this.cash=this.cash.add(cash).setScale(2, BigDecimal.ROUND_HALF_EVEN);;
	}
	
	public boolean pay(BigDecimal cash){
		if(this.cash.compareTo(cash)>=0){
		this.cash=this.cash.subtract(cash).setScale(2, BigDecimal.ROUND_HALF_EVEN);;
		return true;
		}
		return false;
	}
	
	public BigDecimal getCash() {
		return cash;
	}

}

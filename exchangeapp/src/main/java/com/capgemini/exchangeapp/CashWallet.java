package com.capgemini.exchangeapp;

public class CashWallet {
	public float cash;

	public CashWallet(float cash) {
		this.cash = cash;
	}

	public float getCash() {
		return cash;
	}
	
	public void receive(float cash){
		this.cash+=cash;
	}
	
	public boolean pay(float cash){
		if(this.cash-cash>=0){
		this.cash-=cash;
		return true;
		}
		return false;
	}

}

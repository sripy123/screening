package com.ms.screening.dao;

public class Account {
	
	String accountName;
	int amount;
	String accountId;
	
	public Account(String accountName, int amount) {
		this.accountName = accountName;
		this.amount = amount;
	}
	
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
	
}

package co.com.neoris.api.entity;

import java.util.Date;

public class ReportMovement {

	private Date transactionDate;
	
	private String customerName;

	private String accountNumber;
	
	private String typeOfAccount;
	
	private long initialBalance;
	
	private boolean accountStatus;
	
	private long amount;
	
	private long avalaibleBalance;
	
	

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getTypeOfAccount() {
		return typeOfAccount;
	}

	public void setTypeOfAccount(String typeOfAccount) {
		this.typeOfAccount = typeOfAccount;
	}

	public long getInitialBalance() {
		return initialBalance;
	}

	public void setInitialBalance(long initialBalance) {
		this.initialBalance = initialBalance;
	}

	public boolean isAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(boolean accountStatus) {
		this.accountStatus = accountStatus;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public long getAvalaibleBalance() {
		return avalaibleBalance;
	}

	public void setAvalaibleBalance(long avalaibleBalance) {
		this.avalaibleBalance = avalaibleBalance;
	}

	@Override
	public String toString() {
		return "ReportMovement [transactionDate=" + transactionDate + ", customerName=" + customerName
				+ ", accountNumber=" + accountNumber + ", typeOfAccount=" + typeOfAccount + ", initialBalance="
				+ initialBalance + ", accountStatus=" + accountStatus + ", amount=" + amount + ", avalaibleBalance="
				+ avalaibleBalance + "]";
	}
	
	
	
}

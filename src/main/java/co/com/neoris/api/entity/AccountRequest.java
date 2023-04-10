package co.com.neoris.api.entity;

public class AccountRequest {
	
	private String customer_identification;
	
	private String account_number;
	
	private String type_of_account;
	
	private long   initial_balance;
	
	private boolean account_status;

	public String getCustomer_identification() {
		return customer_identification;
	}

	public void setCustomer_identification(String customer_identification) {
		this.customer_identification = customer_identification;
	}

	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	public String getType_of_account() {
		return type_of_account;
	}

	public void setType_of_account(String type_of_account) {
		this.type_of_account = type_of_account;
	}

	public long getInitial_balance() {
		return initial_balance;
	}

	public void setInitial_balance(long initial_balance) {
		this.initial_balance = initial_balance;
	}

	public boolean isAccount_status() {
		return account_status;
	}

	public void setAccount_status(boolean account_status) {
		this.account_status = account_status;
	}

	@Override
	public String toString() {
		return "AccountRequest [customer_identification=" + customer_identification + ", account_number="
				+ account_number + ", type_of_account=" + type_of_account + ", initial_balance=" + initial_balance
				+ ", account_status=" + account_status + "]";
	}

	
	
	
}

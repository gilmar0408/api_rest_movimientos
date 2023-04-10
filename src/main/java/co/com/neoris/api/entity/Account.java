package co.com.neoris.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="bank_account")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_account;
	
	
	private String account_number;
	
	private String type_of_account;
	
	private long initial_balance;
	
	private boolean account_status;
	
	@OneToOne
	@JoinColumn(name="customer")
	private Customer customer;

	public int getId_account() {
		return id_account;
	}

	public void setId_account(int id_account) {
		this.id_account = id_account;
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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Account [id_account=" + id_account + ", account_number=" + account_number + ", type_of_account="
				+ type_of_account + ", initial_balance=" + initial_balance + ", account_status=" + account_status
				+ ", customer=" + customer + "]";
	}


	
	
}

package co.com.neoris.api.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="movement")
public class Movement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_movement;
	
	
	private String account_number;
	
	private Date transaction_date;
	
	private String movement_type;
	
	private long amount;
	
	private long balance;

	public int getId() {
		return id_movement;
	}

	public void setId(int id) {
		this.id_movement = id;
	}

	public String getAccountNumber() {
		return account_number;
	}

	public void setAccountNumber(String accountNumber) {
		this.account_number = accountNumber;
	}

	public Date getDate() {
		return transaction_date;
	}

	public void setDate(Date date) {
		this.transaction_date = date;
	}

	public String getMovementType() {
		return movement_type;
	}

	public void setMovementType(String movementType) {
		this.movement_type = movementType;
	}

	public long getValue() {
		return amount;
	}

	public void setValue(long value) {
		this.amount = value;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Movement [id=" + id_movement + ", accountNumber=" + account_number + ", date=" + transaction_date + ", movementType="
				+ movement_type + ", value=" + amount + ", balance=" + balance + "]";
	}
	
	
}

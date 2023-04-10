package co.com.neoris.api.entity;

import java.util.Date;

public class MovementRequest {

	private String accountNumber;

	private Date date;

	private String movementType;

	private long value;

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMovementType() {
		return movementType;
	}

	public void setMovementType(String movementType) {
		this.movementType = movementType;
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "MovementRequest [accountNumber=" + accountNumber + ", date=" + date + ", movementType=" + movementType
				+ ", value=" + value + "]";
	}
	

}

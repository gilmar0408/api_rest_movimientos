package co.com.neoris.api.entity;

public class AccountResponse {
	
	private String message;
	
	private Account acount;
	
	
	public AccountResponse() {
		
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public Account getAcount() {
		return acount;
	}


	public void setAcount(Account acount) {
		this.acount = acount;
	}


	@Override
	public String toString() {
		return "AccountResponse [message=" + message + ", acount=" + acount + "]";
	}
	
	
	

}

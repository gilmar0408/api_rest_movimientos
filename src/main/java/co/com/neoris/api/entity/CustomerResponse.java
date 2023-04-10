package co.com.neoris.api.entity;

public class CustomerResponse {
	
	
	private String message;
	
	private Customer customer;

	public CustomerResponse() {
		
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "CustomerResponse [message=" + message + ", customer=" + customer + "]";
	}
	
	

}

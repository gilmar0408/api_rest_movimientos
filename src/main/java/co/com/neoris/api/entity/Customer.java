package co.com.neoris.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_customer;
	
	private String full_name;
	
	private String gender;
	
	private int age;
	
	private String identification;
	
	private String address;
	
	private String phone;
	
	private String secret_password;
	
	private boolean customer_status;

	public int getId_customer() {
		return id_customer;
	}

	public void setId_customer(int id_customer) {
		this.id_customer = id_customer;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSecret_password() {
		return secret_password;
	}

	public void setSecret_password(String secret_password) {
		this.secret_password = secret_password;
	}

	public boolean isCustomer_status() {
		return customer_status;
	}

	public void setCustomer_status(boolean customer_status) {
		this.customer_status = customer_status;
	}

	@Override
	public String toString() {
		return "Customer [id_customer=" + id_customer + ", full_name=" + full_name + ", gender=" + gender + ", age="
				+ age + ", identification=" + identification + ", address=" + address + ", phone=" + phone
				+ ", secret_password=" + secret_password + ", customer_status=" + customer_status + "]";
	}

	
	
}

package co.com.neoris.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.neoris.api.entity.Customer;
import co.com.neoris.api.entity.CustomerRequest;
import co.com.neoris.api.entity.CustomerResponse;
import co.com.neoris.api.service.IAccountService;
import co.com.neoris.api.service.ICustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	@Autowired
	private IAccountService serviceAccount;

	@Autowired
	private ICustomerService serviceCustomer;

	@PostMapping("/create")
	public ResponseEntity<CustomerResponse> create(@RequestBody CustomerRequest request) {

		CustomerResponse response;

		if (serviceCustomer.findByIdentification(request.getIdentification()).isPresent()) {

			response = new CustomerResponse();

			response.setMessage(
					"El cliente con identificación " + request.getIdentification() + " ya se encuentra registrado.");

			ResponseEntity<CustomerResponse> resp = new ResponseEntity<CustomerResponse>(response,
					HttpStatus.BAD_REQUEST);

			return resp;
		}

		Customer customer = new Customer();

		customer.setAddress(request.getAddress());
		customer.setAge(request.getAge());
		customer.setGender(request.getGender());
		customer.setIdentification(request.getIdentification());
		customer.setFull_name(request.getFull_name());
		customer.setSecret_password(request.getSecret_password());
		customer.setPhone(request.getPhone());
		customer.setCustomer_status(request.isCustomer_status());
		
		System.out.println(customer);

		CustomerResponse customerResp = serviceCustomer.create(customer);

		return new ResponseEntity<CustomerResponse>(customerResp, HttpStatus.CREATED);

	}

	@PutMapping("/update/{id_customer}")
	public ResponseEntity<CustomerResponse> update(@RequestBody Customer customer,
			@PathVariable("id_customer") int id) {

		if (!serviceCustomer.findByid(id).isPresent()) {

			CustomerResponse response = new CustomerResponse();
			response.setMessage("No se ha encontrado el cliente con id especificado.");

			return new ResponseEntity<CustomerResponse>(response, HttpStatus.BAD_REQUEST);

		}

		Customer customerFinded = serviceCustomer.findByid(id).get();

		customerFinded.setAddress(customer.getAddress());
		customerFinded.setAge(customer.getAge());
		customerFinded.setGender(customer.getGender());
		customerFinded.setIdentification(customer.getIdentification());
		customerFinded.setFull_name(customer.getFull_name());
		customerFinded.setSecret_password(customer.getSecret_password());
		customerFinded.setPhone(customer.getPhone());
		customerFinded.setCustomer_status(customer.isCustomer_status());

		CustomerResponse response = serviceCustomer.update(customerFinded);

		return new ResponseEntity<CustomerResponse>(response, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{identification}")
	public ResponseEntity<CustomerResponse> delete(@PathVariable("identification") String identification) {

		if (!serviceCustomer.findByIdentification(identification).isPresent()) {

			CustomerResponse response = new CustomerResponse();
			response.setMessage("El cliente no se encuentra registrado.");

			return new ResponseEntity<CustomerResponse>(response, HttpStatus.NOT_FOUND);

		}
		
		CustomerResponse response = new CustomerResponse();
		
		response = serviceCustomer.delete(serviceCustomer.findByIdentification(identification).get().getId_customer());

		return new ResponseEntity<CustomerResponse>(response, HttpStatus.OK);
	}

	@GetMapping("/getAll")
	public ResponseEntity<?> getAll() {

		List<Customer> customers = serviceCustomer.findAll();

		ResponseEntity<List<Customer>> response = new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);

		return response;
	}

}

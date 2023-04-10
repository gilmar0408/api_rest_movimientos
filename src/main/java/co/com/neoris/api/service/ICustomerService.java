package co.com.neoris.api.service;

import java.util.List;
import java.util.Optional;

import co.com.neoris.api.entity.Customer;
import co.com.neoris.api.entity.CustomerResponse;

public interface ICustomerService {

	CustomerResponse create(Customer customer);

	CustomerResponse update(Customer customer);

	CustomerResponse delete(int id);

	List<Customer> findAll();

	Optional<Customer> findByIdentification(String identification);
	
	Optional<Customer>  findByid(int id);

}

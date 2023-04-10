package co.com.neoris.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.neoris.api.entity.Customer;
import co.com.neoris.api.entity.CustomerResponse;
import co.com.neoris.api.repository.CustomerRepository;
import co.com.neoris.api.service.ICustomerService;

@Service
public class CustomerService implements ICustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public CustomerResponse create(Customer customer) {

		CustomerResponse response = new CustomerResponse();

		customerRepository.save(customer);

		response.setCustomer(customer);
		response.setMessage("Se ha registrado el cliente con exito!");

		return response;
	}

	@Override
	public CustomerResponse update(Customer customer) {

		CustomerResponse response = new CustomerResponse();
		customerRepository.save(customer);

		response.setCustomer(customer);
		response.setMessage("Se ha actualizado con extio el cliente!");

		return response;
	}

	@Override
	public CustomerResponse delete(int id) {

		Optional<Customer> customerFinded = customerRepository.findById(id);

		customerRepository.delete(customerFinded.get());

		CustomerResponse response = new CustomerResponse();

		response.setCustomer(customerFinded.get());

		response.setMessage("Se ha eliminado correctamente el cliente!");

		return response;
	}

	@Override
	public List<Customer> findAll() {

		List<Customer> customersFinded = customerRepository.findAll();

		return customersFinded;
	}

	@Override
	public Optional<Customer> findByIdentification(String identification) {

		return customerRepository.findByIdentification(identification);
	}

	@Override
	public Optional<Customer> findByid(int id) {

		return customerRepository.findById(id);
	}

}

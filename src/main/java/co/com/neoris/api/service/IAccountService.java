package co.com.neoris.api.service;

import java.util.List;
import java.util.Optional;

import co.com.neoris.api.entity.Account;
import co.com.neoris.api.entity.AccountResponse;

public interface IAccountService {
	
	AccountResponse create(Account account);
	
	AccountResponse update(Account account);
	
	AccountResponse delete(int id);
	
	List<Account>   findAll();
	
	List<Account>   findByCustomer(String indentification);
	
	Optional<Account>   findById(int id);
	
	Optional<Account>   findByAccountNumber (String accountNumber);
	
}

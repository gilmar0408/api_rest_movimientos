package co.com.neoris.api.service.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.neoris.api.entity.Account;
import co.com.neoris.api.entity.AccountResponse;
import co.com.neoris.api.repository.AccountRepository;
import co.com.neoris.api.service.IAccountService;

@Service
public class AccountService implements IAccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public AccountResponse create(Account account) {

		AccountResponse resp = new AccountResponse();
		accountRepository.save(account);

		resp.setMessage("Se ha registrado la cuenta con exito!");
		resp.setAcount(account);

		return resp;
	}

	@Override
	public AccountResponse update(Account account) {

		AccountResponse response = new AccountResponse();
		accountRepository.save(account);

		response.setMessage("Se ha actulizado correctamente la cuenta!");
		response.setAcount(account);

		return response;
	}

	@Override
	public AccountResponse delete(int id) {

		Optional<Account> accountFinded = accountRepository.findById(id);

		accountRepository.delete(accountFinded.get());

		AccountResponse response = new AccountResponse();

		response.setMessage("Se ha eliminado la cuenta correctamente!");
		response.setAcount(accountFinded.get());

		return response;
	}

	@Override
	public List<Account> findAll() {

		List<Account> accountsFinded = accountRepository.findAll();

		return accountsFinded;
	}

	@Override
	public List<Account> findByCustomer(String indentification) {

		List<Account> accounts = accountRepository.findAll();
		
		System.out.println(accounts);

		List<Account> myAccounts = new ArrayList<>();

		for (Account a : accounts) {

			if (a.getCustomer().getIdentification().equals(indentification)) {

				myAccounts.add(a);
			}

		}

		return myAccounts;
	}

	@Override
	public Optional<Account> findById(int id) {

		Optional<Account> account = accountRepository.findById(id);

		return account;
	}

	@Override
	public Optional<Account> findByAccountNumber(String accountNumber) {

		Optional<Account> accountFinded = accountRepository.findByAccountNumber(accountNumber);

		return accountFinded;
	}

}

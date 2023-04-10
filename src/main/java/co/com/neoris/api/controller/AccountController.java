package co.com.neoris.api.controller;

import java.util.List;

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

import co.com.neoris.api.entity.Account;
import co.com.neoris.api.entity.AccountRequest;
import co.com.neoris.api.entity.AccountResponse;
import co.com.neoris.api.entity.Customer;
import co.com.neoris.api.entity.CustomerResponse;
import co.com.neoris.api.service.IAccountService;
import co.com.neoris.api.service.ICustomerService;

@RestController
@RequestMapping("/api/account")
public class AccountController {

	@Autowired
	private IAccountService serviceAccount;

	@Autowired
	private ICustomerService serviceCustomer;

	@PostMapping("/create")
	public ResponseEntity<AccountResponse> create(@RequestBody AccountRequest request) {
		
		
		if (serviceAccount.findByAccountNumber(request.getAccount_number()).isEmpty()) {

		Customer findCust;

		List<Account> findAccounts = serviceAccount.findByCustomer(request.getCustomer_identification());

		System.out.println(findAccounts);

		if (!serviceCustomer.findByIdentification(request.getCustomer_identification()).isPresent()) {

			AccountResponse respAccount = new AccountResponse();

			respAccount.setMessage("El cliente no se encuentra registrado!");

			System.out.println("El cliente no se encuentra registrado!");

			ResponseEntity<AccountResponse> response = new ResponseEntity<AccountResponse>(respAccount,
					HttpStatus.BAD_REQUEST);

			return response;

		} else if (!findAccounts.isEmpty()) {

			findCust = serviceCustomer.findByIdentification(request.getCustomer_identification()).get();

			System.out.println("SI existen cuentas bancarias con la identificación proporcionada!");

			switch (findAccounts.size()) {
			case 1:

				System.out.println("Solo hay una cuenta bancaria con esa identificación.!");

				if (findAccounts.get(0).getType_of_account().equalsIgnoreCase("ahorros")) {

					System.out.println("La cuenta bancaria existente es ahorros.!");

					if (request.getType_of_account().equalsIgnoreCase("corriente")) {

						Account account = new Account();
						account.setAccount_number(request.getAccount_number());
						account.setCustomer(findCust);
						account.setType_of_account(request.getType_of_account());
						account.setInitial_balance(request.getInitial_balance());
						account.setAccount_status(request.isAccount_status());

						AccountResponse respAccount = serviceAccount.create(account);

						ResponseEntity<AccountResponse> response = new ResponseEntity<AccountResponse>(respAccount,
								HttpStatus.CREATED);

						return response;

					} else if (request.getType_of_account().equalsIgnoreCase("ahorros")) {

						AccountResponse respAccount = new AccountResponse();
						respAccount.setMessage("El cliente ya tiene cuenta de ahorros inscrita.");

						ResponseEntity<AccountResponse> response = new ResponseEntity<AccountResponse>(respAccount,
								HttpStatus.PRECONDITION_FAILED);

						return response;

					} else {

						AccountResponse respAccount = new AccountResponse();
						respAccount
								.setMessage("Los parametros elegidos para crear cuenta no cumplen con los requisitos.");

						ResponseEntity<AccountResponse> response = new ResponseEntity<AccountResponse>(respAccount,
								HttpStatus.BAD_REQUEST);

						return response;

					}

				} else if (findAccounts.get(0).getType_of_account().equalsIgnoreCase("corriente")) {

					System.out.println("La cuenta bancaria existente es corriente.!");

					if (request.getType_of_account().equalsIgnoreCase("ahorros")) {

						Account account = new Account();
						account.setAccount_number(request.getAccount_number());
						account.setCustomer(findCust);
						account.setType_of_account(request.getType_of_account());
						account.setInitial_balance(request.getInitial_balance());
						account.setAccount_status(request.isAccount_status());

						AccountResponse respAccount = serviceAccount.create(account);

						ResponseEntity<AccountResponse> response = new ResponseEntity<AccountResponse>(respAccount,
								HttpStatus.CREATED);

						return response;

					} else if (request.getType_of_account().equalsIgnoreCase("corriente")) {

						AccountResponse respAccount = new AccountResponse();
						respAccount.setMessage("El cliente ya tiene cuenta corriente inscrita.");

						ResponseEntity<AccountResponse> response = new ResponseEntity<AccountResponse>(respAccount,
								HttpStatus.PRECONDITION_FAILED);

						return response;

					} else {

						AccountResponse respAccount = new AccountResponse();
						respAccount
								.setMessage("Los parametros elegidos para crear cuenta no cumplen con los requisitos.");

						ResponseEntity<AccountResponse> response = new ResponseEntity<AccountResponse>(respAccount,
								HttpStatus.BAD_REQUEST);

						return response;

					}

				}

				break;

			case 2:

				AccountResponse respAccount = new AccountResponse();
				respAccount.setMessage("El cliente ya tiene 2 cuentas inscritas.");

				System.out.println("El cliente ya tiene 2 cuentas inscritas.!");

				ResponseEntity<AccountResponse> response = new ResponseEntity<AccountResponse>(respAccount,
						HttpStatus.PRECONDITION_FAILED);

				return response;

			default:
				break;
			}

		} else {

			System.out.println("No existen cuentas bancarias con la identificacion proporcionada.!");

			findCust = serviceCustomer.findByIdentification(request.getCustomer_identification()).get();

			Account account = new Account();
			account.setAccount_number(request.getAccount_number());
			account.setCustomer(findCust);
			account.setType_of_account(request.getType_of_account());
			account.setInitial_balance(request.getInitial_balance());
			account.setAccount_status(request.isAccount_status());

			AccountResponse respAccount = serviceAccount.create(account);

			ResponseEntity<AccountResponse> response = new ResponseEntity<AccountResponse>(respAccount, HttpStatus.OK);

			return response;

		}

		AccountResponse respAccount = new AccountResponse();

		respAccount.setMessage("La información enviada se encuentra errada.");

		ResponseEntity<AccountResponse> response = new ResponseEntity<AccountResponse>(respAccount,
				HttpStatus.BAD_REQUEST);

		return response;

		
		}else {
			
			AccountResponse respAccount = new AccountResponse();
			respAccount.setMessage("El numero de cuenta ya se encuentra registrado por favor elija otro.");
			ResponseEntity<AccountResponse> response = new ResponseEntity<AccountResponse>(respAccount, HttpStatus.BAD_REQUEST);
			
			return response;
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<AccountResponse> update(@RequestBody AccountRequest request, @PathVariable("id") int id) {

		AccountResponse respAccount = new AccountResponse();

		ResponseEntity<AccountResponse> response;

		if (serviceAccount.findById(id).isPresent()) {

			Account accountFinded = serviceAccount.findById(id).get();

			accountFinded.setAccount_number(request.getAccount_number());
			accountFinded.setInitial_balance(request.getInitial_balance());
			accountFinded.setAccount_status(request.isAccount_status());
			accountFinded.setType_of_account(request.getType_of_account());

			serviceAccount.update(accountFinded);

			respAccount.setMessage("Se ha actualizado la cuenta correctamente!");
			respAccount.setAcount(accountFinded);

			response = new ResponseEntity<AccountResponse>(respAccount, HttpStatus.OK);

			return response;
		}

		respAccount.setMessage("La cuenta no se encuentra registrada en el sistema");

		response = new ResponseEntity<AccountResponse>(respAccount, HttpStatus.NOT_FOUND);

		return response;
	}

	@DeleteMapping("/delete/{id_account}")
	public ResponseEntity<AccountResponse> delete(@PathVariable("id_account") int id) {

		AccountResponse response = new AccountResponse();

		if (serviceAccount.findById(id).isPresent()) {

			Account accountFinded = serviceAccount.findById(id).get();
			response = serviceAccount.delete(accountFinded.getId_account());

			return new ResponseEntity<AccountResponse>(response, HttpStatus.OK);

		}

		response.setMessage("No se ha encontrado la cuenta a eliminar.");

		return new ResponseEntity<AccountResponse>(response, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/getAll")
	public ResponseEntity<?> getAll() {

		List<Account> accountsFinded = serviceAccount.findAll();

		ResponseEntity<List<Account>> response = new ResponseEntity<List<Account>>(accountsFinded, HttpStatus.OK);

		return response;
	}

}

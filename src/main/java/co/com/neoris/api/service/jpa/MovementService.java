package co.com.neoris.api.service.jpa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.neoris.api.entity.Movement;
import co.com.neoris.api.entity.MovementResponse;
import co.com.neoris.api.entity.ReportMovement;
import co.com.neoris.api.repository.AccountRepository;
import co.com.neoris.api.repository.MovementRepository;
import co.com.neoris.api.service.IMovementService;

@Service
public class MovementService implements IMovementService {

	@Autowired
	private MovementRepository movementRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public MovementResponse create(Movement movement) {

		MovementResponse response = new MovementResponse();

		movementRepository.save(movement);

		response.setMovement(movement);
		response.setMessage("Se ha realizado con exito el movimiento!");

		return response;
	}

	@Override
	public MovementResponse update(Movement movement) {

		MovementResponse response = new MovementResponse();

		movementRepository.save(movement);

		response.setMovement(movement);
		response.setMessage("Se ha actualizado con exito el movimiento!");

		return response;

	}

	@Override
	public MovementResponse delete(int id) {

		MovementResponse response = new MovementResponse();

		Optional<Movement> movementFinded = movementRepository.findById(id);
		
		System.out.println(movementFinded.get());

		movementRepository.delete(movementFinded.get());

		response.setMessage("Se ha eliminado con exito el movimiento!");

		response.setMovement(movementFinded.get());

		return response;
	}

	@Override
	public List<Movement> getAll() {

		return movementRepository.findAll();
	}

	@Override
	public Optional<Movement> findByAccount(String accountNumber) {

		return movementRepository.findByAccount(accountNumber);
	}

	@Override
	public Optional<Movement> findById(int id) {

		return movementRepository.findById(id);
	}

	@Override
	public List<ReportMovement> findByAccountAndDate(String accountNumber, String date) {

		List<Movement> movements = movementRepository.findByAccountAndDate(accountNumber, date);

		String name = accountRepository.findByAccountNumber(accountNumber).get().getCustomer().getFull_name();
		
		boolean accountStatus = accountRepository.findByAccountNumber(accountNumber).get().isAccount_status();
		
		String typeOfAccount = accountRepository.findByAccountNumber(accountNumber).get().getType_of_account();

		List<ReportMovement> reports = new ArrayList<>();

		for (Movement m : movements) {
			
			ReportMovement rep = new ReportMovement();
			
			rep.setAccountNumber(accountNumber);
			rep.setAccountStatus(accountStatus);
			rep.setAmount(m.getValue());
			rep.setAvalaibleBalance(m.getBalance());
			rep.setCustomerName(name);
			rep.setInitialBalance(m.getBalance() - m.getValue());
			rep.setTransactionDate(m.getDate());
			rep.setTypeOfAccount(typeOfAccount);
			
			reports.add(rep);

		}

		return reports;
	}

}

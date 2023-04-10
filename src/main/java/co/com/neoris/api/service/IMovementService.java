package co.com.neoris.api.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import co.com.neoris.api.entity.Movement;
import co.com.neoris.api.entity.MovementResponse;
import co.com.neoris.api.entity.ReportMovement;

public interface IMovementService {

	MovementResponse create(Movement movement);

	MovementResponse update(Movement movement);

	MovementResponse delete(int id);

	List<Movement> getAll();

	Optional<Movement> findByAccount(String accountNumber);
	
	Optional<Movement> findById (int id);
	
	List<ReportMovement>  findByAccountAndDate(String accountNumber , String date);
	
}

package co.com.neoris.api.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
import co.com.neoris.api.entity.Movement;
import co.com.neoris.api.entity.MovementRequest;
import co.com.neoris.api.entity.MovementResponse;
import co.com.neoris.api.entity.ReportMovement;
import co.com.neoris.api.entity.ResponseReportMovement;
import co.com.neoris.api.service.IAccountService;
import co.com.neoris.api.service.ICustomerService;
import co.com.neoris.api.service.IMovementService;

@RestController
@RequestMapping("api/movements")
public class MovementController {

	@Autowired
	public IMovementService serviceMovement;

	@Autowired
	public IAccountService serviceAccount;

	@Autowired
	public ICustomerService serviceCustomer;

	@PostMapping("/create")
	public ResponseEntity<MovementResponse> create(@RequestBody MovementRequest request) {

		if (!accountValid(request.getAccountNumber())) {

			MovementResponse resp = new MovementResponse();

			resp.setMessage("La cuenta ingresada no existe.");

			return new ResponseEntity<MovementResponse>(resp, HttpStatus.BAD_REQUEST);

		} else if (serviceAccount.findByAccountNumber(request.getAccountNumber()).get().isAccount_status()) {

			if (request.getMovementType().equalsIgnoreCase("debito")) {

				Account accountFinded = serviceAccount.findByAccountNumber(request.getAccountNumber()).get();

				if (accountFinded.getInitial_balance() - request.getValue() < 0) {

					MovementResponse response = new MovementResponse();
					response.setMessage("El saldo de la cuenta es insuficiente.");

					return new ResponseEntity<MovementResponse>(response, HttpStatus.BAD_REQUEST);

				} else {

					long actualBalance = accountFinded.getInitial_balance() - request.getValue();

					accountFinded.setInitial_balance(actualBalance);

					serviceAccount.update(accountFinded);

					Movement movement = new Movement();
					movement.setAccountNumber(request.getAccountNumber());
					movement.setDate(request.getDate());
					movement.setMovementType(request.getMovementType());
					movement.setValue(0 - request.getValue());
					movement.setBalance(actualBalance);

					MovementResponse response = serviceMovement.create(movement);

					return new ResponseEntity<MovementResponse>(response, HttpStatus.OK);

				}

			} else if (request.getMovementType().equalsIgnoreCase("credito")) {

				Account accountFinded = serviceAccount.findByAccountNumber(request.getAccountNumber()).get();

				long actualBalance = accountFinded.getInitial_balance() + request.getValue();

				accountFinded.setInitial_balance(actualBalance);

				serviceAccount.update(accountFinded);

				Movement movement = new Movement();
				movement.setAccountNumber(request.getAccountNumber());
				movement.setDate(request.getDate());
				movement.setMovementType(request.getMovementType());
				movement.setValue(request.getValue());
				movement.setBalance(actualBalance);

				MovementResponse response = serviceMovement.create(movement);

				return new ResponseEntity<MovementResponse>(response, HttpStatus.OK);

			}

		} else {

			MovementResponse resp = new MovementResponse();

			resp.setMessage("La cuenta se encuentra bloqueada.");

			return new ResponseEntity<MovementResponse>(resp, HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);

		}

		MovementResponse resp = new MovementResponse();

		resp.setMessage("Ocurrió un error con los parametros ingresados.");

		return new ResponseEntity<MovementResponse>(resp, HttpStatus.BAD_REQUEST);

	}

	@PutMapping("/update/{id_Movement}")
	public ResponseEntity<MovementResponse> update(@RequestBody MovementRequest request,
			@PathVariable("id_Movement") int id) {

		MovementResponse respMovement = new MovementResponse();

		ResponseEntity<MovementResponse> response;

		if (serviceMovement.findById(id).isPresent()) {

			Movement movementFinded = serviceMovement.findById(id).get();

			movementFinded.setAccountNumber(request.getAccountNumber());
			movementFinded.setDate(request.getDate());
			movementFinded.setMovementType(request.getMovementType());
			movementFinded.setValue(request.getValue());

			MovementResponse respMove = serviceMovement.update(movementFinded);

			response = new ResponseEntity<MovementResponse>(respMove, HttpStatus.OK);

			return response;

		}

		respMovement.setMessage("El movimiento no se encuentra registrado.");

		response = new ResponseEntity<MovementResponse>(respMovement, HttpStatus.NOT_FOUND);

		return response;
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<MovementResponse> delete(@PathVariable("id") int id) {
		
		System.out.println(id);

		MovementResponse respMovement = new MovementResponse();

		ResponseEntity<MovementResponse> response;

		if (serviceMovement.findById(id).isPresent()) {

			respMovement = serviceMovement.delete(id);

			response = new ResponseEntity<MovementResponse>(respMovement, HttpStatus.OK);

			return response;

		}

		respMovement.setMessage("El movimiento no se encuentra registrado.");

		response = new ResponseEntity<MovementResponse>(respMovement, HttpStatus.NOT_FOUND);

		return response;
	}

	@GetMapping("/getAll")
	public ResponseEntity<?> getAll() {

		List<Movement> movementsFinded = serviceMovement.getAll();

		ResponseEntity<List<Movement>> response = new ResponseEntity<List<Movement>>(movementsFinded, HttpStatus.OK);

		return response;
	}

	@GetMapping("/reports/{identification}/{date}")
	public ResponseEntity<ResponseReportMovement> getReportMovement(
			@PathVariable("identification") String identification, @PathVariable("date") String date) {
		
		


		ResponseReportMovement response = new ResponseReportMovement();

		if (!serviceCustomer.findByIdentification(identification).isPresent()) {

			response.setMessage("El cliente especificado no se encuentra registrado.");

			ResponseEntity<ResponseReportMovement> responseEntity = new ResponseEntity<ResponseReportMovement>(response,
					HttpStatus.NOT_FOUND);

			return responseEntity;

		} else if (serviceAccount.findByCustomer(identification).isEmpty()) {

			response.setMessage("El cliente no tiene cuentas inscritas.");

			ResponseEntity<ResponseReportMovement> responseEntity = new ResponseEntity<ResponseReportMovement>(response,
					HttpStatus.NOT_FOUND);

			return responseEntity;
		}

		response.setMessage("Se ha generado el siguiente reporte para el cliente especificado!");

		List<Account> accountNumbers = serviceAccount.findByCustomer(identification);

		if (accountNumbers.size() == 1) {

			List<ReportMovement> reportMovements = serviceMovement
					.findByAccountAndDate(accountNumbers.get(0).getAccount_number(), date);

			response.setMovements(reportMovements);

			ResponseEntity<ResponseReportMovement> responseEntity = new ResponseEntity<ResponseReportMovement>(response,
					HttpStatus.OK);

			return responseEntity;

		}

		List<ReportMovement> reportMovements1 = serviceMovement
				.findByAccountAndDate(accountNumbers.get(0).getAccount_number(), date);

		List<ReportMovement> reportMovements2 = serviceMovement
				.findByAccountAndDate(accountNumbers.get(1).getAccount_number(), date);

		reportMovements1.addAll(reportMovements2);

		response.setMovements(reportMovements1);

		ResponseEntity<ResponseReportMovement> responseEntity = new ResponseEntity<ResponseReportMovement>(response,
				HttpStatus.OK);

		return responseEntity;
	}

	public boolean accountValid(String accountNumber) {

		if (serviceAccount.findByAccountNumber(accountNumber).isPresent()) {

			return true;
		}

		return false;
	}

}

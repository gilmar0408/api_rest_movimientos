package co.com.neoris.api.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.com.neoris.api.entity.Movement;
import jakarta.websocket.server.PathParam;

public interface MovementRepository extends JpaRepository<Movement, Integer> {

	@Query(value = "SELECT * FROM movement m WHERE m.account_number LIKE %:number%", nativeQuery = true)
	public Optional<Movement> findByAccount(@PathParam("number") String number);

	@Query(value = "SELECT * FROM movement m WHERE m.account_number LIKE %:number%  AND m.transaction_date LIKE %:datetx%", nativeQuery = true)
	public List<Movement> findByAccountAndDate(@PathParam("number") String number, @PathParam("datetx") String datetx);

}

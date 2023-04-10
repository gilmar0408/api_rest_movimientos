package co.com.neoris.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.com.neoris.api.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{
	
	@Query(value="SELECT * FROM bank_account a WHERE a.account_number LIKE %:number%",nativeQuery = true)
	public Optional<Account> findByAccountNumber(@Param("number") String number);

}

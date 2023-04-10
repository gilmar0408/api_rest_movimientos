package co.com.neoris.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.com.neoris.api.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	@Query(value="SELECT * FROM customer c WHERE c.identification LIKE %:number%" , nativeQuery = true)
	public Optional<Customer>  findByIdentification(@Param("number") String number );
	 

}

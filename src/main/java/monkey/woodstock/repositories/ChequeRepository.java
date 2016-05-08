package monkey.woodstock.repositories;

import java.util.List;

import monkey.woodstock.domain.Cheque;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by alp on 25/12/15.
 *
 * @Configuration tells the Spring Framework this is a Java configuration class.
 * @EnableAutoConfiguration tells Spring Boot to do its auto configuration magic.
 * This is what has Spring Boot automatically create the Spring Beans with sensible defaults for our tests.
 * @EntityScan tells Hibernate  the packages to look for JPA Entities.
 * @EnableJpaRepositories enables the auto configuration of Spring Data JPA.
 * @EnableTransactionManagement Enables Spring’s annotation driven transaction management
 */
public interface ChequeRepository extends CrudRepository<Cheque, Integer>{
	
	@Query("from cheque where banco_id=:bancoid")
	public List<Cheque> findByBanco(@Param("bancoid") Integer bancoId);
}

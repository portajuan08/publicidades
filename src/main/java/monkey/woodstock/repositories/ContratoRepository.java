package monkey.woodstock.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import monkey.woodstock.domain.Contrato;

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
public interface ContratoRepository extends CrudRepository<Contrato, Integer>{
	
	@Query("from contrato where cliente_id=:clienteid")
	public Contrato findByCliente(@Param("clienteid") Integer clienteId);
}

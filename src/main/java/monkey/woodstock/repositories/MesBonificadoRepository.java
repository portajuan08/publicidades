package monkey.woodstock.repositories;

import org.springframework.data.repository.CrudRepository;

import monkey.woodstock.domain.MesBonificado;

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
public interface MesBonificadoRepository extends CrudRepository<MesBonificado, Integer>{
}

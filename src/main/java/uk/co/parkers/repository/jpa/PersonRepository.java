package uk.co.parkers.repository.jpa;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import uk.co.parkers.model.jpa.Person;


public interface PersonRepository extends PagingAndSortingRepository<Person, Long>, QueryDslPredicateExecutor<Person> {
	
	Person findOneByName(String name);

}

package uk.co.parkers.repository.jpa;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import uk.co.parkers.model.jpa.ValidSetValue;


public interface ValidSetValueRepository extends PagingAndSortingRepository<ValidSetValue, String>, QueryDslPredicateExecutor<ValidSetValue> {

}

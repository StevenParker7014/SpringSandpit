package uk.co.parkers.repository.jpa;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import uk.co.parkers.model.jpa.Child;


public interface ChildRepository extends PagingAndSortingRepository<Child, Long>, QueryDslPredicateExecutor<Child> {

}

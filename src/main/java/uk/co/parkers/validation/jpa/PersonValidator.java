package uk.co.parkers.validation.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uk.co.parkers.domain.web.jpa.PersonCriteria;
import uk.co.parkers.repository.jpa.PersonRepository;
import uk.co.parkers.validation.AbstractValidator;

@Component
public class PersonValidator extends AbstractValidator<PersonCriteria, PersonRepository> {
	
	@Autowired
	public PersonValidator(PersonRepository repository) throws ClassNotFoundException {
		super(repository);
	}


}

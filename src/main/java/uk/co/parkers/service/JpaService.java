package uk.co.parkers.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uk.co.parkers.model.jpa.Person;
import uk.co.parkers.repository.jpa.ChildRepository;
import uk.co.parkers.repository.jpa.PersonRepository;

@Transactional
@Service
public class JpaService {
	
	@Autowired
	PersonRepository personRepository;

	@Autowired
	ChildRepository childRepository;
	
	
	public Person savePerson(Person person){
		return personRepository.save(person);
	} 
	
	public List<Person> savePersons(List<Person> persons){
		List<Person> toReturn = new ArrayList<>();
		personRepository.save(persons).forEach(p -> toReturn.add(p));
		return toReturn;
	}

	public Person findPersonByName(String name) {
		return personRepository.findOneByName(name);
	} 
	
	// TODO child stuff

}
